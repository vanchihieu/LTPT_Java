package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {
    @Id
    protected String ISBN;
    protected String name;
    @Column(name = "publish_year")
    protected int publishYear;
    @Column(name = "num_of_pages")
    protected int numOfPages;
    protected Double price;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    protected Publisher publisher;

    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    protected Set<String> authors;

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", numOfPages=" + numOfPages +
                ", price=" + price +
                ", publisher=" + publisher +
                '}';
    }
}
