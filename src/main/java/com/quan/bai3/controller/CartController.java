package com.quan.bai3.controller;

import com.quan.bai3.domain.CartEntity;
import com.quan.bai3.domain.CartHolder;
import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.repository.ProductRepository;
import com.quan.bai3.service.CartService;
import com.quan.bai3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private CartHolder cartHolder;
@Autowired
private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public String showCart(Model model) {
        List<CartEntity> cartEntities = getCartEntity(cartHolder.getCartEntities());
        model.addAttribute("cart", cartEntities);
        return "Cart";
    }

    private List<CartEntity> getCartEntity(List<CartEntity> cartEntities) {
        Set<Long> productIds = cartEntities.stream()
                .map(cartEntity -> cartEntity.getProductEntity().getProductID())
                .collect(Collectors.toSet());

        List<ProductEntity> productEntities = productService.findAllByIdIn(productIds);

        // Map productEntities to a Map for easy lookup
        Map<Long, ProductEntity> productEntityMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getProductID, Function.identity()));

        // Set each CartEntity's ProductEntity based on the product ID
        cartEntities.forEach(item -> {
            Long productId = item.getProductEntity().getProductID();
            ProductEntity productEntity = productEntityMap.get(productId);
            item.setProductEntity(productEntity);
        });

        return cartEntities;
    }
    @GetMapping("remove")
    public String removeCart(Model model, @RequestParam("cartItemID") String id){
        List<CartEntity> entities = cartHolder.removeItem(Integer.parseInt(id));
        model.addAttribute("cart",entities);
        return "cart";
    }


    @PostMapping
    public String addToCart(@ModelAttribute ProductEntity product, Model model) {

        cartHolder.addToCart(product);


        List<ProductEntity> products = productService.listAll();
        model.addAttribute("products", products);
        return "Home";
    }
    @PostMapping("check-out")
    public String checkOut() {

        cartService.createOrder();
        return "Cart";
    }
}
