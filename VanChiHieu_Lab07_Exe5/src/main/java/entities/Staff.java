package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "staff_id")),
})
//@PrimaryKeyJoinColumn(name ="id")
@Table(name = "staffs")
public class Staff extends Person {
    //	@PrimaryKeyJoinColumns({@PrimaryKeyJoinColumn(name ="id", referencedColumnName   ="staff_id")})

    @Column(columnDefinition = "tinyint", length = 1)
    private byte active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Staff(String firstName, String lastName, Contact contact, byte active, Staff manager, Set<Order> orders, Store store) {
        super(firstName, lastName, contact);
        this.active = active;
        this.manager = manager;
        this.orders = orders;
        this.store = store;
    }


}