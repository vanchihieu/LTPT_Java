package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String candidate_id;
    private String description;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String gender;
    private String email;
    private String phone;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "position_id")
    private entities.Position position;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidate")
    private Set<entities.Certificate> certificates;

    public Candidate(String candidate_id, String description, String fullName, int yearOfBirth, String gender, String email, String phone, entities.Position position) {
        this.candidate_id = candidate_id;
        this.description = description;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }


//    public Candidate(String candidate_id, String description, String fullName, int yearOfBirth, String gender, String email, String phone, String position_id) {
//        this.candidate_id = candidate_id;
//        this.description = description;
//        this.fullName = fullName;
//        this.yearOfBirth = yearOfBirth;
//        this.gender = gender;
//        this.email = email;
//        this.phone = phone;
//        this.position = new entities.Position(position_id, "", "", 0.0, 0);
//    }
}
