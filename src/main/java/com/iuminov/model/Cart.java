package com.iuminov.model;

public class Cart {

    private Long userId;
    private Long productId;
    private String token;

    public Cart() {
    }

    public Cart(Long userId, Long productId, String token) {
        this.userId = userId;
        this.productId = productId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
