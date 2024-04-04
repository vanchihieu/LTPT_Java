package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "store_name", columnDefinition = "nvarchar(255)")
    private String name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<Staff> staffs;

}