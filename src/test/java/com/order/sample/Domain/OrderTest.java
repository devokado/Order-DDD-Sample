package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class OrderTest {

    @Test
    @DisplayName("Create domain Orders class Test")
    public void createOrdersTest(){
        RecipientAddress recipientAddress = new RecipientAddress("name","address",new CityName("tehran"), Country.IRAN);
        Order newOrder = new Order(Instant.now(),Currency.Rial,recipientAddress);
        assertThat(newOrder.id() != null);

    }
    @Test
    @DisplayName("Create order item")
    public void createOrderItem(){
//        OrderItem orderItem = new OrderItem(UUID.randomUUID(),"desc",100L);
//        assertThat(orderItem.itemDescription().equals("desc"));

    }
    @Test
    @DisplayName("")
    public void recipientAddress(){

    }

}
