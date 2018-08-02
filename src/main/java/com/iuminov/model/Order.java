package com.iuminov.model;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private Double price;
    private Long userId;
    private OrderStatus status;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;

    public Order() {
    }

    public Order(Double price, Long userId, OrderStatus status, LocalDateTime createTime, LocalDateTime paymentTime) {
        this.price = price;
        this.userId = userId;
        this.status = status;
        this.createTime = createTime;
        this.paymentTime = paymentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
}
