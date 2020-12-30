package com.order.sample.Domain.SeedWork.Assembler;


import com.order.sample.Domain.Order;
import com.order.sample.Infrastructure.Jpa.OrderDTO;
import org.springframework.beans.BeanUtils;



public interface OrderAssembler {

    static OrderDTO toPO(Order order){
        OrderDTO orderDTO =new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

}
