package top.gabin.demo.pay.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class PayInfo {
    @Id
    private Long id;
    private boolean pay;
}
