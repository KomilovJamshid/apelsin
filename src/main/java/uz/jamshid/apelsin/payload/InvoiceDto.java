package uz.jamshid.apelsin.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDto {
    private Double amount;
    private Date issued;
    private Date due;
    private Integer orderId;
}
