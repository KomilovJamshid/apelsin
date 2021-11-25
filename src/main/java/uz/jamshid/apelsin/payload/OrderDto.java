package uz.jamshid.apelsin.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Date date;
    private Integer customerId;
}
