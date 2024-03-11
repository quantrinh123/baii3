package com.quan.bai3.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID", nullable = false)
    private Long productID;

    private String productName;
    private String productDescription;
    private double unitPrice;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product1")
    List<OrderDetailEntity> orderDetail;

    public ProductEntity(String imagePath) {
        this.imagePath = imagePath;
    }

    public ProductEntity() {

    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<OrderDetailEntity> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }
    private String imagePath;

    @Override
    public String toString() {
        return "Product:" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", unitPrice=" + unitPrice
                ;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
