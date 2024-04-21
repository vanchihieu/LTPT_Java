package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reception")
public class Reception extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String shift;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}