package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "OnsiteCourse")
@PrimaryKeyJoinColumn(name = "CourseID")
public class OnsiteCourse extends Course {
    @Column(name = "Days")
    private String days;
    @Column(name = "Location")
    private String location;
    @Column(name = "Time")
    private LocalDateTime time;

    public OnsiteCourse(int credits, String title, String days, String location, LocalDateTime time) {
        super(credits, title);
        this.days = days;
        this.location = location;
        this.time = time;
    }
}