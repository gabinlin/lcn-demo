package top.gabin.demo.pay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gabin.demo.pay.entity.PayInfo;

@Repository
public interface PayInfoDao extends JpaRepository<PayInfo, Long> {
}
