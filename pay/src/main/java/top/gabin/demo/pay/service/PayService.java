package top.gabin.demo.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.gabin.demo.pay.dao.PayInfoDao;
import top.gabin.demo.pay.entity.PayInfo;
import top.gabin.demo.pay.msg.Order;

@Service
public class PayService {

    @Autowired
    private PayInfoDao payInfoDao;

    @Transactional
    public void pay(Order order) {
        PayInfo payInfo = new PayInfo();
        payInfo.setId(order.getId());
        payInfo.setPay(true);
//        int a = 1/0;
        payInfoDao.save(payInfo);
    }
}
