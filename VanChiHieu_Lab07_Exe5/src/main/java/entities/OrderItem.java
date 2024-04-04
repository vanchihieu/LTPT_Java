package entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "order_items")
public class OrderItem {
    @Column(nullable = false)
    private int quantity;
    @Column(name = "list_price", columnDefinition = "decimal(10,2)")
    private double listPrice;
    @Column(columnDefinition = "decimal(4,2)")
    private double discount;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}