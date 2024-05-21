package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "positions")
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "position_id")
    private String id;
    private String name;
    private String description;
    private Double salary;
    private int number;

    @ToString.Exclude
    @OneToMany(mappedBy = "position")
    private Set<entities.Candidate> appliedCandidates;

    public Position() {
    }

    public Position(String id, String name, String description, Double salary, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.number = number;
    }
}
