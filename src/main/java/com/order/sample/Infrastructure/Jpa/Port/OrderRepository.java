package com.order.sample.Infrastructure.Jpa.Port;

import com.order.sample.Domain.OrderId;
import com.order.sample.Infrastructure.Jpa.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findById(UUID id);

}
