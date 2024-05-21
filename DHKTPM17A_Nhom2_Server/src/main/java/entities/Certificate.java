package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Certificate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "certificate_id")
    private String id;
    private String name;
    private String organization;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private entities.Candidate candidate;


}
