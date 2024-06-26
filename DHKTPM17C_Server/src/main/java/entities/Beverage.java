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
public class Beverage extends entities.Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private entities.Size size;
    @Column(name = "supplier_name")
    private String supplierName;

    @OneToOne
    @JoinColumn(name = "id")
    private entities.Item item;

    @Override
    public String toString() {
        return "Beverage{" +
                "size=" + size +
                ", supplierName='" + supplierName + '\'' +
                ", item=" + item +
                '}';
    }
}
