package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Department")
public class Department {
    @Id
    @Column(name = "DepartmentID", nullable = false)
    private Integer id;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

}