package com.quan.bai3.service.impl;

import com.quan.bai3.domain.CartEntity;
import com.quan.bai3.domain.CartHolder;
import com.quan.bai3.entity.OrderDetailEntity;
import com.quan.bai3.entity.OrderEntity;
import com.quan.bai3.entity.ProductEntity;
import com.quan.bai3.repository.OrderDetailRepository;
import com.quan.bai3.repository.OrderRepository;
import com.quan.bai3.repository.ProductRepository;
import com.quan.bai3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {
@Autowired
    private CartHolder cartHolder;
@Autowired
private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public void createOrder() {
        Map<Long, Integer> cart = cartHolder.getCart();

        Map<Long, ProductEntity> productEntityMap = productRepository.findAllById(cart.keySet())
                .stream()
                .collect(Collectors.toMap(ProductEntity::getProductID, Function.identity()));


        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerAddress("dn");
        order.setCustomerName("quan");
        order = orderRepository.save(order);


        OrderEntity finalOrder = order;
        cart.forEach((k, v) -> {
            OrderDetailEntity detailEntity = new OrderDetailEntity();
            detailEntity.setProduct1(productEntityMap.get(k));
            detailEntity.setQuantity(v);
            detailEntity.setOrder1(finalOrder);
            orderDetailRepository.save(detailEntity);
        });
    }
}
