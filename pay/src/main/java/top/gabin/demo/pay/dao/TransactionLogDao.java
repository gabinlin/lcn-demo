package top.gabin.demo.pay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gabin.demo.pay.entity.TransactionLog;

@Repository
public interface TransactionLogDao extends JpaRepository<TransactionLog, Long> {
    long countById(String transactionId);
}
