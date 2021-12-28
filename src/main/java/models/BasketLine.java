package models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketLine {

    private Product product;
    private int quantity;
    private BigDecimal totalOrderBasket;

    public BasketLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalOrderBasket = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void addQuantity(int quantity) {
        this.setQuantity(this.getQuantity() + quantity);
    }
}
