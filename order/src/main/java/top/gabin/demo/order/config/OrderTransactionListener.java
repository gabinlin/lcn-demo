package top.gabin.demo.order.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.gabin.demo.order.dao.TransactionLogDao;
import top.gabin.demo.order.entity.Order;
import top.gabin.demo.order.service.OrderService;

@Component
@Slf4j
public class OrderTransactionListener implements TransactionListener {

    @Autowired
    OrderService orderService;
    @Autowired
    TransactionLogDao transactionLogDao;

    /**
     * 发送half msg 返回send ok后调用的方法
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("开始执行本地事务....");
        LocalTransactionState state;
        try {
            String body = new String(message.getBody());
            Order order = JSONObject.parseObject(body, Order.class);
            orderService.order(order, message.getTransactionId());
            // 返回commit后，消息能被消费者消费
//            state = LocalTransactionState.COMMIT_MESSAGE;
//            state = LocalTransactionState.ROLLBACK_MESSAGE;
            // 开启这个之后则必须回查
            state = LocalTransactionState.UNKNOW;
//            TimeUnit.MINUTES.sleep(1);
            log.info("本地事务已提交。{}", message.getTransactionId());


        } catch (Exception e) {
            log.info("执行本地事务失败。{}", e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    /**
     * 回查 走的方法
     *
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        // 回查多次失败 人工补偿。提醒人。发邮件的。
        log.info("开始回查本地事务状态。{}", messageExt.getTransactionId());
        LocalTransactionState state;
        String transactionId = messageExt.getTransactionId();
        if (transactionLogDao.countById(transactionId) > 0) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.UNKNOW;
        }
        log.info("结束本地事务状态查询：{}", state);
        return state;
    }
}