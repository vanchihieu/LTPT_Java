package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
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

    public Food(Type type, int preparationTime, int servingTime, Item item) {
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
        this.item = item;
    }

//    @Override
//    public String toString() {
//        return "Food{" +
//                "type=" + type +
//                ", preparationTime=" + preparationTime +
//                ", servingTime=" + servingTime +
//                ", item=" + item +
//                '}';
//    }
}
