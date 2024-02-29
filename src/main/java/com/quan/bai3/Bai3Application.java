package com.quan.bai3;

import com.quan.bai3.config.Config;
import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Bai3Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ProductService service = context.getBean("productService", ProductService.class);
        Scanner sc = new Scanner(System.in);
        List<ProductEntity> allProducts = service.productList();

        System.out.println("All Products:");
        for (ProductEntity product : allProducts) {
            System.out.println(product.toString());
        }
        for(int i = 0; i < 10; i++){
            ProductEntity product = new ProductEntity();
            product.setProductDescription("quan dep"+ i);
            product.setProductName("quan"+i);
            product.setUnitPrice(400 + i);
            service.saveProduct(product);
        }


        System.out.println("Please input page number:");
        int pageNumber = 0;
        System.out.println("PHan trang");
        for(int i = 0; i < service.paginationProducts(pageNumber).getTotalPages(); i++){
            System.out.println("Trang"+ i);
            service.paginationProducts(i).forEach(product -> {
                System.out.println(product.toString());
            });
        }
        System.out.println("sap xep giam dan");
        for(int i = 0; i < service.paginationProducts(pageNumber).getTotalPages(); i++){
            System.out.println("Trang"+ i);
            service.paginationAndSortProducts(i);
        }
        System.out.println(" tổng số lượng : " + service.paginationProducts(pageNumber).getTotalElements());
        System.out.println(" tổng số trang " + service.paginationProducts(pageNumber).getTotalPages());

        System.out.println("Please input name of product: ");
        String productName = sc.nextLine();
        List<ProductEntity> searchResults = service.searchByName(productName);
        for (ProductEntity product : searchResults) {
            System.out.println("Search result:");
            System.out.println(product.toString());
        }
    }
}
