package com.nikola.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikola.spring.entities.AwardEntity;
import com.nikola.spring.entities.BookEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AuthorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String firstName;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String lastName;
    @NotEmpty
    @Size(min = 3 , max = 40)
    private String pseudonym;
    @NotEmpty
    private String dateOfBirth;
    @NotEmpty
    private String nationality;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Integer> booksIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Integer> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(List<Integer> booksIds) {
        this.booksIds = booksIds;
    }
}
