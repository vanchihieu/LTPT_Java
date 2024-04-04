package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_id", columnDefinition = "VARCHAR(50)")
    private String id;
    @Column( columnDefinition = "VARCHAR(255)")
    private String location;
    @Column(name = "dept_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Staff> staff;
}