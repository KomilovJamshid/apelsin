package uz.jamshid.apelsin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 8, scale = 2)
    private Double amount;

    @Column(nullable = false)
    private Date issued;

    @Column(nullable = false)
    private Date due;

    @OneToOne
    private Order order;
}
