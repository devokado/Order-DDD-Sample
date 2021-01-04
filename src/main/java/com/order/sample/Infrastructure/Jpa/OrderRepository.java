package com.order.sample.Infrastructure.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderDTO, UUID> {
    Optional<OrderDTO> findById(UUID id);

}
