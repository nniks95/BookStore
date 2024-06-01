package com.nikola.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikola.spring.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class BookDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ISBN;
    @NotEmpty
    @Size(max = 60)
    private String title;
    @NotNull
    private Integer authorId;
    @NotNull
    private Integer genreId;
    @NotEmpty
    @Size(min = 3, max = 40)
    private String language;
    @NotNull
    private Integer publisherId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Byte available;
    @NotNull
    @DecimalMin("0.00")
    private Double price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> reviewsIds;
    @NotNull
    @Range(min = 0, max = 1000)
    private Integer numberOfPages;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> awardsIds;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> wishlistsIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Integer> getReviewsIds() {
        return reviewsIds;
    }

    public void setReviewsIds(List<Integer> reviewsIds) {
        this.reviewsIds = reviewsIds;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Integer> getAwardsIds() {
        return awardsIds;
    }

    public void setAwardsIds(List<Integer> awardsIds) {
        this.awardsIds = awardsIds;
    }

    public List<Integer> getWishlistsIds() {
        return wishlistsIds;
    }

    public void setWishlistsIds(List<Integer> wishlistsIds) {
        this.wishlistsIds = wishlistsIds;
    }
}
