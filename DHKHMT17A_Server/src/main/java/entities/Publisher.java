package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publisher {
    @Id
    @Column(name = "publisher_id")
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
