package top.gabin.demo.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gabin.demo.order.entity.Order;
import top.gabin.demo.order.entity.TransactionLog;

@Repository
public interface TransactionLogDao extends JpaRepository<TransactionLog, Long> {
    long countById(String transactionId);
}
