package ksi.springbooks.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idc;
    private String description;
    @OneToMany
    @JoinColumn(name="publisher_idp")
    private List<Book> books;

    public Category() {
    }

    public Category(Long idc, String description) {
        this.idc = idc;
        this.description = description;
    }

    public Long getIdc() {
        return idc;
    }
    public void setIdc(Long idb) {
        this.idc = idb;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String title) {
        this.description = title;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
