package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "beverages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Beverage extends Item implements Serializable{
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "supplier_name")
    private String supplierName;

    @OneToOne
    @JoinColumn(name = "id")
    private Item item;
}
