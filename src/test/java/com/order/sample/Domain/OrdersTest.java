package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class OrdersTest {

    @Test
    @DisplayName("Create domain Orders class Test")
    public void createOrdersTest(){
        Set<OrderItem> itemSet = new HashSet<>();
        itemSet.add(new OrderItem(UUID.randomUUID(),"desc",100L));
        RecipientAddress recipientAddress = new RecipientAddress("name","address",new CityName("tehran"), Country.IRAN);
        Orders newOrder = new Orders(UUID.randomUUID(), Instant.now(),Currency.Rial,itemSet,recipientAddress);
        assertThat(newOrder.currency().equals("Rial"));

    }
    @Test
    @DisplayName("Create order item")
    public void createOrderItem(){
        OrderItem orderItem = new OrderItem(UUID.randomUUID(),"desc",100L);
        assertThat(orderItem.itemDescription().equals("desc"));

    }
}
