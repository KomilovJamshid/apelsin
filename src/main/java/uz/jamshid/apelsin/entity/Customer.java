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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 14)
    @Column(nullable = false)
    private String name;

    @Size(max = 3)
    @Column(nullable = false)
    private String country;

    @Column
    private String address;

    @Size(max = 50)
    @Column
    private String phone;
}
