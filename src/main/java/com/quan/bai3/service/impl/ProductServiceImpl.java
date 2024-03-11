package com.quan.bai3.service.impl;

import com.quan.bai3.domain.UpSertProduct;
import com.quan.bai3.entity.ProductEntity;

import com.quan.bai3.repository.ProductRepository;
import com.quan.bai3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    @Override
    public List<ProductEntity> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity> paginationProducts(int pageNumber) {
        return productRepository.findAll(PageRequest.of(pageNumber, 5));
    }


    @Override
    public void sortPagination(int pageNumber) {
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
    @Override
    public List<ProductEntity> findAllByIdIn(Set<Long> productIds)  {
        return productRepository.findAllById(productIds);
    }
    @Override
    public void create(UpSertProduct product) throws IOException {
        ProductEntity entity = new ProductEntity();
        entity.setProductName(product.getName());
        entity.setUnitPrice(product.getPrice());
        entity.setImagePath(generateImagePath(product.getFile()));
       productRepository.save(entity);

    }

    @Override
    public ProductEntity getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }



    private String generateImagePath(MultipartFile file) throws IOException {


        File file1 = new File("/Users/LENOVO/IdeaProjects/bai3/" + file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        }

        return "/resources/"+file.getOriginalFilename();
    }
    private String getFileExtension(String originalFilename) {
        return "";
    }
    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

}

