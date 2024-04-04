package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "StudentGrade")
public class StudentGrade {
    @Id
    private Integer enrollmentID;
    @Column(name = "Grade")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;

}