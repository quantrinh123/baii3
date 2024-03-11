package com.quan.bai3.controller;

import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.service.ProductService;
import org.hibernate.sql.model.jdbc.UpsertOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.quan.bai3.domain.UpSertProduct;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("Admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String showProducts(Model model){
        List<ProductEntity> products = productService.listAll();
        model.addAttribute("products", products);
        return "Admin";
    }
    @GetMapping("creates")
    public String showAddProduct(Model model){
        model.addAttribute("name","");
        model.addAttribute("image","");
        model.addAttribute("price","");
        model.addAttribute("description","");
        return "Admin";
    }
   @PostMapping("creates")
    public String addProduct(@ModelAttribute UpSertProduct product) throws IOException {
        productService.create(product);
        return"redirect:/Admin";
   }
   @GetMapping("delete/{productId}")
   public String deleteProduct(@PathVariable Long productId) {
       productService.deleteProductById(productId);
       return "redirect:/Admin";
   }
}
