package uz.jamshid.apelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighDemandProductDto {
    private Integer productId;
    private BigInteger quantity;
}
