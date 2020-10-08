package top.gabin.demo.order.contoller;

import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gabin.demo.order.entity.Order;
import top.gabin.demo.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create-order")
    public void createOrder(@RequestBody Order order) throws MQClientException {
        orderService.order(order);
    }
    
}
