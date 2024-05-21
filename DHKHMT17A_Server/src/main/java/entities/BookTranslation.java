package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_translations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class BookTranslation extends Book{
    @Column(name = "translate_name")
    private String translateName;
    private String language;

    @OneToOne
    @JoinColumn(name = "ISBN")
    private Book book;
}
