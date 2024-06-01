package com.nikola.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikola.spring.entities.BookEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class GenreDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    @Size(min = 3, max = 60)
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> booksIds;

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

    public List<Integer> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(List<Integer> booksIds) {
        this.booksIds = booksIds;
    }
}
