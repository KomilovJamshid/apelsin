package uz.jamshid.apelsin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 10)
    @Column
    private String name;

    @Size(max = 20)
    @Column
    private String description;

    @Column(precision = 6, scale = 2)
    private Double price;

    @Size(max = 1024)
    @Column
    private String photo;

    @ManyToOne
    private Category category;
}
