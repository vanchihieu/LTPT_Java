package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OfficeAssignment")
public class OfficeAssignment {
    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID", unique = true)
    private Instructor instructor;
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;
}