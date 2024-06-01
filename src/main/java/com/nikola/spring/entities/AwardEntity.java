package com.nikola.spring.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "award")
public class AwardEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(name = "date_of_award", nullable = false)
    private LocalDate dateOfAward;
    @ManyToMany(mappedBy = "awards")
    @Transient
    private List<BookEntity> books;

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

    public LocalDate getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(LocalDate dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
