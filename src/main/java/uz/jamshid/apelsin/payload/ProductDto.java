package uz.jamshid.apelsin.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private Integer photoId;
    private Integer categoryId;
}