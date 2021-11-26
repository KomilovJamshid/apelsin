package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Double amount;
    private Date issued;
    private Date due;
    private Integer orderId;
}
