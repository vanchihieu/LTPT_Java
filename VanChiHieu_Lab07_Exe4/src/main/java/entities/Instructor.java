package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "Instructor")
//@DiscriminatorValue("Instructor")
public class Instructor extends Person {
    @Column(name = "HireDate", columnDefinition = "DATETIME")
    private LocalDateTime hireDate;
    @OneToOne(mappedBy = "instructor")
    private OfficeAssignment officeAssignment;

    @ManyToMany(mappedBy = "instructors")
    private Set<Course> course;
    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }

}