package com.lrm.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookDto {

    @Length(min = 3)
    @NotBlank
    private String author;
    @Length(max = 20)
    @NotBlank
    private String description;
    @NotBlank
    private String name;
    @NotNull
    private Integer status;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
