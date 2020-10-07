package top.gabin.demo.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LCN_ORDER")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
