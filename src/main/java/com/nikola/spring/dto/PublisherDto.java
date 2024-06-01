package com.nikola.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikola.spring.entities.BookEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PublisherDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    @Size(min = 3 , max = 60)
    private String name;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String country;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String city;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String state;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String postcode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> bookIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }
}
