package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
//@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private Double price;
    private String description;
    @Column(name = "on_special")
    private boolean onSpecial;

    @ManyToMany
    @JoinTable(
        name = "items_ingredients",
        joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    )
    private Set<Ingredient> ingredient;

    public Item(String id, String name, Double price, String description, boolean onSpecial) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.onSpecial = onSpecial;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }
}
