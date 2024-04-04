package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staffs")
public class Staff {
    @Id
    @Column(name = "staff_id", columnDefinition = "BIGINT")
    private long id;
    private int age;
    @Column(name = "staff_name", columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(name = "refers", columnDefinition = "VARCHAR(255)")
    private String references;

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "number", nullable = false)
    private Set<String> phoneNumbers;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Profile profile;


}