package uz.jamshid.apelsin.payload;

import lombok.Data;

@Data
public class DetailDto {
    private Short quantity;
    private Integer orderId;
    private Integer productId;
}
