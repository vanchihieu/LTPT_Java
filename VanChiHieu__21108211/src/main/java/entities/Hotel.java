package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "hotels")
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "hotel_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    private Set<Employee> employees;
}