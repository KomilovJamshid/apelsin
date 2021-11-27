package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersWithoutInvoiceDto {
    private Integer orderId;
    private Date orderDate;
    private Double totalPrice;
}
