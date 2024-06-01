package com.nikola.spring.dto;


import com.nikola.spring.entities.CustomerEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

public class RequestLoyaltyCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
