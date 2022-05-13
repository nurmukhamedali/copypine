package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="product_id", unique = true)
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;
}