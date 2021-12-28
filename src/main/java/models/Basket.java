package models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Basket {

    private List<BasketLine> basketLine;
    private BigDecimal totalOrderPrice;

    public Basket() {
        basketLine = new ArrayList<>();
    }

    private void updateTotalPrice() {
        totalOrderPrice = new BigDecimal(0);
        for (BasketLine line : basketLine) {
            totalOrderPrice = totalOrderPrice.add(line.getTotalOrderBasket());
        }
    }

    public void addBasketLine(BasketLine newBasketLine) {
        for (BasketLine line : basketLine) {
            if(line.getProduct().getName().equals(newBasketLine.getProduct().getName())) {
                line.addQuantity(newBasketLine.getQuantity());
                updateTotalPrice();
                return;
            }
        }
        basketLine.add(newBasketLine);
        updateTotalPrice();
    }
}
