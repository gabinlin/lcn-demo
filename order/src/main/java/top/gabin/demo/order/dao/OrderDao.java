package top.gabin.demo.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gabin.demo.order.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
}
