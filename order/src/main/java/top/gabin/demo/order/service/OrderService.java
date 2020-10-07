package top.gabin.demo.order.service;

import io.seata.spring.annotation.GlobalTransactional;
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
    @GlobalTransactional
    public void order() {
        Order order = new Order();
        orderDao.save(order);
        System.out.println(order.getId());
//        int a = 1 / 0;
        restTemplate.postForEntity("http://pay-service/pay", order, String.class);
    }

}
