package models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String name;
    private BigDecimal price;

    public Product(String productName, BigDecimal productPrice) {
        this.name = productName;
        this.price = productPrice;
    }
}
