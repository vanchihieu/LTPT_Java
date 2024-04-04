package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Course")
public abstract class Course {
    @Id
    @Column(name = "CourseID", nullable = false)
    private Integer id;
    @Column(name = "Credits")
    private int credits;
    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "CourseID"),
            inverseJoinColumns = @JoinColumn(name = "PersonID")
    )
    private Set<Instructor> instructors;

    public Course(int credits, String title) {
        this.credits = credits;
        this.title = title;
    }
}