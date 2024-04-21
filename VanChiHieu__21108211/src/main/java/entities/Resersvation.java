package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "resersvations")
public class Resersvation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "resersvation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "check_in_date")
    private LocalDateTime checkInDate;
    @Column(name = "check_out_date")
    private LocalDateTime checkOutDate;
    private String status;
    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
}