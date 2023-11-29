package ksi.springbooks.models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idb;
    private String title;

    @ManyToOne
    private Publisher publisher;
    @ManyToOne
    private Category category;

    public Book() {
    }

    public Book(Long idb, String title, Publisher publisher, Category category) {
        this.idb = idb;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
    }

    public Long getIdb() {
        return idb;
    }

    public void setIdb(Long idb) {
        this.idb = idb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
