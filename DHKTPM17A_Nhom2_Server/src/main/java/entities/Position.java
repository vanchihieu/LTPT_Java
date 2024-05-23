package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "positions")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<entities.Candidate> getAppliedCandidates() {
        return appliedCandidates;
    }

    public void setAppliedCandidates(Set<entities.Candidate> appliedCandidates) {
        this.appliedCandidates = appliedCandidates;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", number=" + number +
                '}';
    }
}
