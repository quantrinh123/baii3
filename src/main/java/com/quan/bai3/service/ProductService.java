package com.quan.bai3.service;

import com.quan.bai3.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductEntity> productList();
    Page<ProductEntity> paginationProducts(int pageNumber);
    void paginationAndSortProducts(int pageNumber);
    void saveProduct(ProductEntity product);

    List <ProductEntity> searchByName(String name);
}
