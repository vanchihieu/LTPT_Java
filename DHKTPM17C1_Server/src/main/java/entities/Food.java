package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "foods")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Food extends Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;

    @OneToOne
    @JoinColumn(name = "id")
    private Item item;

    public Food() {
    }

    public Food(Type type, int preparationTime, int servingTime, Item item) {
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
        this.item = item;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getServingTime() {
        return servingTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
