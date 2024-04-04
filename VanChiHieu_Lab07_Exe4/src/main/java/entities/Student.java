package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@DiscriminatorValue("Student")
public class Student extends Person {
    private LocalDateTime enrollmentDate;

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}