package com.quan.bai3.service.impl;

import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.repository.ProductRepository;
import com.quan.bai3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductEntity> productList() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity> paginationProducts(int pageNumber) {
        return productRepository.findAll(PageRequest.of(pageNumber, 5));
    }


    @Override
    public void paginationAndSortProducts(int pageNumber) {
        Page<ProductEntity> page = productRepository.findAll(PageRequest.of(pageNumber, 5));
        List<ProductEntity> products = new ArrayList<>(page.getContent());
        products.sort(Comparator.comparing(ProductEntity::getUnitPrice).reversed());
        products.forEach(product -> {
            System.out.println(product);
        });
    }

    @Override
    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductEntity> searchByName(String name) {
        return productRepository.findByProductName(name);
    }
}
