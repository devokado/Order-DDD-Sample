package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersTest {

    @Test
    @DisplayName("Create domain Orders class Test")
    public void createOrdersTest(){
        Date date = new Date();
        Timestamp ts =new Timestamp(date.getTime());
        Currency currency ;
        Orders orders = new Orders();
        assertThat(orders);
    }
}
