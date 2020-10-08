package top.gabin.demo.order.service;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.gabin.demo.order.config.TransactionProducer;
import top.gabin.demo.order.dao.OrderDao;
import top.gabin.demo.order.dao.TransactionLogDao;
import top.gabin.demo.order.entity.Order;
import top.gabin.demo.order.entity.TransactionLog;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TransactionLogDao transactionLogDao;
    @Autowired
    private TransactionProducer producer;

    @Transactional
    public void order(Order order, String transactionId) {
        // 1、保存订单
        orderDao.save(order);

        // 2.写入事务日志
        TransactionLog log = new TransactionLog();
        log.setId(transactionId);
        log.setBusiness("order");
        log.setForeignKey(String.valueOf(order.getId()));
        transactionLogDao.save(log);
    }

    public void order(Order order) throws MQClientException {
        order.setName(System.nanoTime() + "");
        producer.send(JSON.toJSONString(order),"order");
    }
}
