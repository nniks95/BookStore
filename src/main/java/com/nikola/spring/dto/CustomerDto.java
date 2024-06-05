package com.nikola.spring.dto;

import com.nikola.spring.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CustomerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    private String customerPhone;
    private Integer shippingAddressId;
    private Integer userId;
    private Integer cartId;
    private Integer loyaltyCardId;
    private List<Integer> persistanceLoginsIds;
    private Integer wishlistId;
    private Integer reviewId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getLoyaltyCardId() {
        return loyaltyCardId;
    }

    public void setLoyaltyCardId(Integer loyaltyCardId) {
        this.loyaltyCardId = loyaltyCardId;
    }

    public List<Integer> getPersistanceLoginsIds() {
        return persistanceLoginsIds;
    }

    public void setPersistanceLoginsIds(List<Integer> persistanceLoginsIds) {
        this.persistanceLoginsIds = persistanceLoginsIds;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}
