package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int id;
    @Column(name = "order_status", columnDefinition = "tinyint", length = 1)
    private byte orderStatus;
    @Column(name = "order_date", columnDefinition = "date")
    private LocalDate orderDate;
    @Column(name = "required_date", columnDefinition = "date")
    private LocalDate requiredDate;
    @Column(name = "shipped_date", columnDefinition = "date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}