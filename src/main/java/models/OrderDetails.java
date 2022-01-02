package models;

import lombok.Data;

@Data
public class OrderDetails {
    private String shippingMethod;
    private String paymentMethod;
    private String orderReference;

    private String firstName;
    private String lastName;
    private String zipCode;
    private String address;
    private String city;
    private String country;
}
