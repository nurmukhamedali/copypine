package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalCost;
    private Long totalItems;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private User customer;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> items;
}