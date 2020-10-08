package top.gabin.demo.pay.compent;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.gabin.demo.pay.msg.Order;
import top.gabin.demo.pay.service.PayService;

import java.util.List;

@Component
@Slf4j
public class OrderListener implements MessageListenerConcurrently {

    @Autowired
    PayService payService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        log.info("消费者线程监听到消息。");
        try {
//            System.out.println(1 / 0);
            for (MessageExt message : list) {
                log.info("开始处理订单数据，置为成功支付....");
                Order order = JSONObject.parseObject(message.getBody(), Order.class);
                payService.pay(order);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("处理消费者数据发生异常。{}", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}