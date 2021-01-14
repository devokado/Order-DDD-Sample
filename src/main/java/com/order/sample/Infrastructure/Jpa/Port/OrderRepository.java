package com.order.sample.Infrastructure.Jpa.Port;

import com.order.sample.Domain.OrderId;
import com.order.sample.Infrastructure.Jpa.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query(value = "select o from OrderEntity o where o.status = true and o.id =:id")
    Optional<OrderEntity> findById(UUID id);



}
