package com.order.sample.Domain;

import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import com.order.sample.Infrastructure.Jpa.OrderEntity;
import com.order.sample.Infrastructure.Jpa.OrderItemEntity;
import com.order.sample.Infrastructure.Jpa.Port.OrderRepository;
import com.order.sample.Infrastructure.Jpa.RecipientAddressEntity;
import com.order.sample.Presentation.Rest.Request.OrderDTO;
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
import static org.junit.Assert.assertEquals;

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
    @DisplayName("Convert order to DTO")
    public void ConvertOrderToEntity(){
       Order order = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name","somewhere",new CityName("tehran"),Country.IRAN));
       OrderEntity entity = OrderEntity.fromOrder(order);
       assertThat(entity.equals(order));
    }
    @Test
    @DisplayName("Convert OrderReq to Order Domain")
    public void ConvertOrderDTOToOrderDomain(){
        OrderDTO dto = new OrderDTO();
        dto.setCurrency("Rial");
        dto.setName("Nejatian");
        dto.setAddressLine1("someWhere");
        dto.setCity("Tehran");
        dto.setCountry("IRAN");
        dto.setProductId("someThing");
        dto.setItemDescription("Something pretty expensive");
        dto.setItemPrice(100L);
        dto.setQuantity(2);
        Order order = Order.toDomainModel(dto);
        assertThat(order.currency().equals(dto.getCurrency()));
    }
    @Test
    @DisplayName("Convert DTO to Order")
    public void convertEntityToOrder(){
        OrderEntity entity = new OrderEntity(UUID.randomUUID(),Instant.now(),"Rial","RECEIVED",new RecipientAddressEntity("name","someWhere",new CityName("tehran"),Country.IRAN),null);
        Order order = entity.toOrder();
       assertEquals(entity.getId(),UUID.fromString(order.id().toUUID()));
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
    List<OrderEntity> expectedList = new ArrayList<>();
    Order order1 = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name1","somewhere1",new CityName("tehran1"),Country.IRAN));
    Order order2 = new Order(Instant.now(),Currency.Rial,new RecipientAddress("name2","somewhere2",new CityName("tehran2"),Country.IRAN));

    expectedList.add(OrderEntity.fromOrder(order1));
    expectedList.add(OrderEntity.fromOrder(order2));
        Mockito.when(orderRepository.findAll()).thenReturn(expectedList);
    List<Order> foundOrders = orderInterface.findAll();

        assertThat(!foundOrders.isEmpty());
        assertThat(foundOrders.size() == 2);
    }

    @Test
    @DisplayName("Create orderItem")
    public void createOrderItem(){
        ProductId productId = DomainObjectId.randomId(ProductId.class);
        OrderItem orderItem = new OrderItem(productId,"Toothpaste",100L,10);
        assertThat(orderItem.id() != null);

    }
    @Test
    @DisplayName("Convert OrderItem to OrderItem Entity")
    public void convertOrderItemToOrderItemEntity(){
        ProductId productId = DomainObjectId.randomId(ProductId.class);
        OrderItem orderItem = new OrderItem(productId,"Toothpaste",100L,10);
        OrderItemEntity entity = OrderItemEntity.fromOrderItem(orderItem);
        assertThat(entity.equals(orderItem));
    }
    @Test
    @DisplayName("Convert OrderItem Entity to OrderItem")
    public void convertOrderItemEntityToOrderItem(){
        ProductId productId = DomainObjectId.randomId(ProductId.class);
        OrderItemEntity entity = new OrderItemEntity(UUID.randomUUID(),UUID.fromString(productId.toUUID()),"Item desc",10L,2);
        //OrderItem item =
    }

}
