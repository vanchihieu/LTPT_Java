package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food extends entities.Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private entities.Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;

    @OneToOne
    @JoinColumn(name = "id")
    private entities.Item item;

//    public Food(entities.Type type, int preparationTime, int servingTime, entities.Item item) {
//        this.type = type;
//        this.preparationTime = preparationTime;
//        this.servingTime = servingTime;
//        this.item = item;
//    }

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
