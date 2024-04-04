package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @Column(name = "project_id", columnDefinition = "VARCHAR(50)")
    private String id;
    @Column(name = "project_name", columnDefinition = "VARCHAR(255)")
    private String name;
    private double budget;
}