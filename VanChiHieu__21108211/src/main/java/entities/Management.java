package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "management")
public class Management extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "management_level")
    private String managementLevel;
}