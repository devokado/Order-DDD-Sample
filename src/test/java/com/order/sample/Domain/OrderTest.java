package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import com.order.sample.Infrastructure.Jpa.OrderDTO;
import com.order.sample.Infrastructure.Jpa.OrderItemDTO;
import com.order.sample.Infrastructure.Jpa.RecipientAddressDTO;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderTest {

    @Mock
    private ModelMapper modelMapper;

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

    }
    @Test
    @DisplayName("Convert order to DTO")
    public void ConvertOrderToDTO(){
       Order order = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name","somewhere",new CityName("tehran"),Country.IRAN));
       OrderDTO dto = OrderDTO.fromOrder(order);
       assertThat(dto.equals(order));
    }
    @Test
    @DisplayName("Convert OrderReq to Order Domain")
    public void ConvertOrderReqToOrderDomain(){
        OrderReq req = new OrderReq();
        req.setCurrency("Rial");
        req.setName("Nejatian");
        req.setAddressLine1("someWhere");
        req.setCity("Tehran");
        req.setCountry("IRAN");
        req.setProductId("someThing");
        req.setItemDescription("Something pretty expensive");
        req.setItemPrice(100L);
        req.setQuantity(2);
        Order order = Order.toDomainModel(req);
        assertThat(order.currency().equals(req.getCurrency()));
    }


}
