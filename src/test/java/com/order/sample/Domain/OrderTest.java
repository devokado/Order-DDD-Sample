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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
//        OrderItem orderItem = new OrderItem(UUID.randomUUID(),"desc",100L);
//        assertThat(orderItem.itemDescription().equals("desc"));

    }
    @Test
    @DisplayName("Convert order to DTO")
    public void ConvertOrderToDTO(){
        Set<OrderItemDTO> itemDTOS =new HashSet<>();
        Order expectedOrder = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name","add",new CityName("tehran"),Country.IRAN));
        OrderDTO orderDTO = new OrderDTO(UUID.randomUUID().toString(),Instant.now(),Currency.Rial.toString(),"received",new RecipientAddressDTO("name","add",new CityName("tehran"),Country.IRAN),itemDTOS);
        assertThat(orderDTO.asOrder()).isEqualTo(expectedOrder);
    }



}
