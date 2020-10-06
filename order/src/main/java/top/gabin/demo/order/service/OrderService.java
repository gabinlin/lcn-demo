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
//        restTemplate.getForObject("")
        Order order = new Order();
        orderDao.save(order);
    }

}
