package top.gabin.demo.pay.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.gabin.demo.pay.dao.PayInfoDao;
import top.gabin.demo.pay.entity.PayInfo;

@Service
public class PayService {

    @Autowired
    private PayInfoDao payInfoDao;

    @Transactional
    @LcnTransaction
    public void pay(Long id) {
        PayInfo payInfo = new PayInfo();
        payInfo.setId(id);
        payInfo.setPay(true);
//        int a = 1/0;
        payInfoDao.save(payInfo);
    }


}
