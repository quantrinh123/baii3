package com.quan.bai3.entity;

import jakarta.persistence.*;

@Entity

public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailsID", nullable = false)
    private Long orderDetailId;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private OrderEntity order1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductEntity product1;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrder1() {
        return order1;
    }

    public void setOrder1(OrderEntity order1) {
        this.order1 = order1;
    }

    public ProductEntity getProduct1() {
        return product1;
    }

    public void setProduct1(ProductEntity product1) {
        this.product1 = product1;
    }

}
