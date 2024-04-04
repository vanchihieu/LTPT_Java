package entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "model_year", columnDefinition = "smallint")
    private int modelYear;
    @Column(name = "list_price", columnDefinition = "decimal(10,2)")
    private double listPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="brand_id")
    private Brand brand;

    public Product(int id, String name, int modelYear, double listPrice, Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
        this.category = category;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelYear=" + modelYear +
                ", listPrice=" + listPrice +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}