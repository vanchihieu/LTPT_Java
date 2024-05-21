package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reviews {
    private int rating;
    private String comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
