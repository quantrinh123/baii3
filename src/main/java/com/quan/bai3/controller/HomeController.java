package com.quan.bai3.controller;

import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping ("home")
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String showProducts(Model model){
        List<ProductEntity> products = productService.listAll();
        model.addAttribute("products", products);
        return "Home";
    }
}
