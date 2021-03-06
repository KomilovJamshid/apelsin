package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrongDateInvoiceDto {
    private Integer invoiceId;
    private Date issued;
    private Date orderDate;
    private Integer orderId;
}
