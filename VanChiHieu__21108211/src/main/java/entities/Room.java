package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rooms")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_number")
    private int roomNumber;
    @Column(name = "price_per_night")
    private Double pricePerNight;
    private String status;
    @Column(name = "room_type")
    private String roomType;

    @OneToOne(mappedBy = "room")
    private Resersvation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}