package com.nikola.spring.dto;

import com.nikola.spring.entities.BookEntity;
import com.nikola.spring.entities.CustomerEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class WishlistDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer customerId;
    private List<Integer> booksIds;

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

    public List<Integer> getBookIds() {
        return booksIds;
    }

    public void setBookIds(List<Integer> booksIds) {
        this.booksIds = booksIds;
    }
}
