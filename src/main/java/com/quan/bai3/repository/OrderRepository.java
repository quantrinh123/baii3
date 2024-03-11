package com.quan.bai3.repository;

import com.quan.bai3.entity.OrderDetailEntity;
import com.quan.bai3.entity.OrderEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
