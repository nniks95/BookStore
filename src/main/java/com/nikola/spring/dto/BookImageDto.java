package com.nikola.spring.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class BookImageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    //    @NotEmpty
//    @Size(min = 3,max = 70)
    private String name;
    //    @NotEmpty
//    @Size(min = 3,max = 70)
    private String contentType;
    //@NotNull
    private Long size;
    private byte[] data;
    @NotNull
    private Integer book_id;

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
