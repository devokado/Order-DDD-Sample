package com.order.sample.Infrastructure.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersDTO,Long> {

}
