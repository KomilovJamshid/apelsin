package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLastOrderDto {
    private Integer customerId;
    private String name;
    private Date lastOrderDate;
}
