package entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "staff_projects")
public class StaffProject {
    @Id
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}