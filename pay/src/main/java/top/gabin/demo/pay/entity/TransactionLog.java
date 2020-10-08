package top.gabin.demo.pay.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
public class TransactionLog implements Serializable {
    /**
     * 事务ID
     */
    @Id
    private String id;

    /**
     * 业务标识
     */
    private String business;

    /**
     * 对应业务表中的主键
     */
    private String foreignKey;

    private static final long serialVersionUID = 1L;
}