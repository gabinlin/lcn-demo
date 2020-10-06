package top.gabin.demo.pay.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gabin.demo.pay.service.PayService;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Data
    static class Order {
        Long id;
    }

    @PostMapping
    public ResponseEntity pay(@RequestBody Order order) {
        System.out.println(order.id);
        payService.pay(order.getId());
        return ResponseEntity.ok("支付成功");
    }

}
