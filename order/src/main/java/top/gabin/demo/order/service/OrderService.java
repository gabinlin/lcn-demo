package top.gabin.demo.order.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import top.gabin.demo.order.dao.OrderDao;
import top.gabin.demo.order.entity.Order;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    @LcnTransaction
    public void order() {
        Order order = new Order();
        orderDao.save(order);
        System.out.println(order.getId());
//        int a = 1 / 0;
        restTemplate.postForEntity("http://pay-service/pay", order, String.class);
    }

    @Transactional
    @LcnTransaction
    public void testTCC() {
        System.out.println("Order 测试TCC");
        restTemplate.getForEntity("http://pay-service/pay", String.class);
    }
}
