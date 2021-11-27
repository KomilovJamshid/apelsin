package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverpaidInvoiceDto {
    private Integer invoiceId;
    private Integer totalPayment;
    private Integer actualAmount;
    private Integer reimburseAmount;
}
