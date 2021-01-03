package com.order.sample.Domain;

import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import com.order.sample.Infrastructure.Jpa.OrderDTO;
import com.order.sample.Infrastructure.Jpa.OrderRepository;
import com.order.sample.Infrastructure.Jpa.RecipientAddressDTO;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderTest {
    @MockBean
    OrderRepository orderRepository;

    @Autowired
    OrderInterface orderInterface;


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
    @Test
    @DisplayName("Convert DTO to Order")
    public void convertDtoToOrder(){
        OrderDTO dto = new OrderDTO(UUID.randomUUID(),Instant.now(),"Rial","RECEIVED",new RecipientAddressDTO("name","someWhere",new CityName("tehran"),Country.IRAN),null);
        Order order = dto.toOrder();
        assertThat(order.equals(dto));
    }
    @Test
    @DisplayName("Convert Order to OrderResponse")
    public void convertOrderToOrderRes(){
        Order order = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name","somewhere",new CityName("tehran"),Country.IRAN));
        OrderResponse res = OrderResponse.from(order);
        assertThat(res.equals(order));
    }
    @Test
    @DisplayName("Find all orders")
    public void findAllOrders(){
    List<OrderDTO> expectedList = new ArrayList<>();
    Order order1 = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name1","somewhere1",new CityName("tehran1"),Country.IRAN));
    Order order2 = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name2","somewhere2",new CityName("tehran2"),Country.IRAN));

    expectedList.add(OrderDTO.fromOrder(order1));
    expectedList.add(OrderDTO.fromOrder(order2));
        Mockito.when(orderRepository.findAll()).thenReturn(expectedList);
    List<Order> foundOrders = orderInterface.findAll();

        assertThat(!foundOrders.isEmpty());
        assertThat(foundOrders.size() == 2);
    }

}
