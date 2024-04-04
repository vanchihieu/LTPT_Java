package entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "first_name", columnDefinition = "nvarchar(50)")
    protected String firstName;
    @Column(name = "last_name", columnDefinition = "nvarchar(50)")
    protected String lastName;

    @Embedded
    private Contact contact;

    public Person(String firstName, String lastName, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
    }
}