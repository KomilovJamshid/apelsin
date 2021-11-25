package uz.jamshid.apelsin.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDto {
    private Timestamp time;
    private Double amount;
    private Integer invoiceId;
}
