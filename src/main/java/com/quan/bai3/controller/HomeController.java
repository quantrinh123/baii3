package com.quan.bai3.controller;

import com.quan.bai3.entity.OrderEntity;
import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.service.OrderService;
import com.quan.bai3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("home")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @GetMapping
    public String showProducts(Model model){
        List<ProductEntity> products = productService.listAll();

        model.addAttribute("products", products);

        return "Home";
    }
    @GetMapping("/price")
    public String getProductsByPriceRange(Model model, @RequestParam(required = false) String priceRange) {
        List<ProductEntity> products;
        if (priceRange != null) {
            String[] range = priceRange.split("-");
            Double minPrice = Double.parseDouble(range[0]);
            Double maxPrice = Double.parseDouble(range[1]);
            products = productService.getProductsByPriceRange(minPrice, maxPrice);
        } else {
            products = productService.listAll();
        }
        model.addAttribute("products", products);
        return "Home";
    }


}
