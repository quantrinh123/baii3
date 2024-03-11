package com.quan.bai3.repository;

import com.quan.bai3.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByProductName(String name);

//    List<ProductEntity> findAllByIdIn(Set<Long> productIds);
}
