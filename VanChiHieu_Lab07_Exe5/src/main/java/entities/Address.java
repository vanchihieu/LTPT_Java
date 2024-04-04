package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    @Column(columnDefinition = "nvarchar(50)")
    private String city;
    @Column(columnDefinition = "nvarchar(10)")
    private String state;
    @Column(name ="zip_code",columnDefinition = "nvarchar(5)")
    private String zipcode;
}