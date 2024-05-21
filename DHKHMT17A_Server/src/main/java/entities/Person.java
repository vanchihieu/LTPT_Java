package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "people")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    @Id
    @Column(name = "person_id")
    private String id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "professional_title")
    private String professionalTitle;
}
