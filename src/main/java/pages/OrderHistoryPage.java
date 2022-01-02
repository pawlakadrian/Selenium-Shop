package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryPage extends BasePage{

    @FindBy (css = "tbody tr")
    List<WebElement> orders;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public List<SingleOrderHistoryPage> getListOrdersInHistory() {
        List<SingleOrderHistoryPage> orders = new ArrayList<>();

        for (WebElement order : this.orders) {
            orders.add(new SingleOrderHistoryPage(order, driver));
        }

        return orders;
    }

    public List<SingleOrderHistoryPage> getSpecifiedOrder(String referenceNumber) {
        List<SingleOrderHistoryPage> orderLineInHistory = getListOrdersInHistory()
                .stream()
                .filter(order -> order.orderNumber().contains(referenceNumber))
                .collect(Collectors.toList());
        return orderLineInHistory;
    }

    public String dateToCompare() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String todayDate = dateFormat.format(calendar.getTime());
        return todayDate;
    }
}
