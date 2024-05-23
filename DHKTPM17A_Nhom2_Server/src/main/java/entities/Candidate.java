package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "candidates")
//@NoArgsConstructor
//@Getter
//@Setter
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

    public Candidate() {
    }

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

    public String getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public entities.Position getPosition() {
        return position;
    }

    public void setPosition(entities.Position position) {
        this.position = position;
    }

    public Set<entities.Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<entities.Certificate> certificates) {
        this.certificates = certificates;
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
