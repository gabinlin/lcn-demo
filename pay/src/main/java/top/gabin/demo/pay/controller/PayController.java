package top.gabin.demo.pay.controller;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
        try {
            // 这里一定要捕获异常，不但是遵循dao抛出异常，service打印异常且抛出，controller必须捕获异常
            // 而且也是为了测试restapi调用成功，但是内里service异常的情况下是否会回滚
            payService.pay(order.getId());
            return ResponseEntity.ok("支付成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @TccTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public ResponseEntity testTcc() {
//        int a = 1 / 0;
        System.out.println("pay 测试tcc");
        return ResponseEntity.ok("支付成功");
    }

    public void confirmTestTcc() {
        System.out.println("confirm tcc");
    }

    public void cancelTestTcc() {
        System.out.println("cancel tcc");
    }

}
