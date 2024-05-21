package entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    private String description;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private entities.Candidate candidates;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "position_id")
    private entities.Position positions;
}
