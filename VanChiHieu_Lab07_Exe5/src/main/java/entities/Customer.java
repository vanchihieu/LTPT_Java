package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
@AttributeOverrides({@AttributeOverride(name ="id",column = @Column(name ="customer_id"))})
public class Customer extends Person{

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Customer(String firstName, String lastName, Contact contact, Address address, Set<Order> orders) {
        super(firstName, lastName, contact);
        this.address = address;
        this.orders = orders;
    }
}