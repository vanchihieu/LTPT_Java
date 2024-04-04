package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "OnlineCourse")
@PrimaryKeyJoinColumn(name = "CourseID")
public class OnlineCourse extends Course {
    @Column(name = "URL", columnDefinition = "NVARCHAR(100)")
    private String url;

    public OnlineCourse(int credits, String title, String url) {
        super( credits, title );
        this.url = url;
    }
}