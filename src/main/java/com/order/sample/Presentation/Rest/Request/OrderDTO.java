package com.order.sample.Presentation.Rest.Request;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.Instant;

public class OrderDTO implements Serializable {
    @NotNull(message = "Currency can not be null")
    @Pattern(regexp = "Rial|Dollar|EUR")
    private String currency;
    @NotNull(message = "name can not be null")
    private String name;
    @NotNull(message = "Address can not be null")
    private String addressLine1;
    @NotNull(message = "City can not be null")
    private String city;
    @NotNull(message = "Country can not be null")
    @Pattern(regexp = "IRAN|GERMANY|ITALY|TURKEY")
    private String country;
    @NotNull(message = "productId can not be null")
    private String productId;
    private String itemDescription;
    @NotNull(message = "item price can not be null")
    private Long itemPrice;
    @NotNull(message = "quantity can not be null")
    private Integer quantity;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order toDomainModel(){
        return new Order(Instant.now(), Currency.valueOf(currency),new RecipientAddress(name,addressLine1,new CityName(city), Country.valueOf(country)));
    }

}
