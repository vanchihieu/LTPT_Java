package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "staff_id", columnDefinition = "BIGINT")
    private long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String avatar;

    @OneToOne
    @MapsId
    @JoinColumn(name = "staff_id",unique = true)
    private Staff staff;
}