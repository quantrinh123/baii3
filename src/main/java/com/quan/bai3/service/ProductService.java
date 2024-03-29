package com.quan.bai3.service;

import com.quan.bai3.domain.UpSertProduct;
import com.quan.bai3.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {
     List<ProductEntity> listAll();
    Page<ProductEntity> paginationProducts(int pageNumber);
    void sortPagination(int pageNumber);
    void saveProduct(ProductEntity product);

    List <ProductEntity> searchByName(String name);


    List<ProductEntity> findAllByIdIn(Set<Long> productIds);



    void create(UpSertProduct product) throws IOException;

    ProductEntity getById(Long id);



    void deleteProductById(Long productId);


    List<ProductEntity> getProductsByPriceRange(Double minPrice, Double maxPrice);
}

