package ksi.springbooks.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idp;
    private String name;
    private String address;
    @OneToMany
    @JoinColumn(name="publisher_idp")
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(Long idp, String name, String address) {
        this.idp = idp;
        this.name = name;
        this.address = address;
    }

    public Long getIdp() {
        return idp;
    }

    public void setIdp(Long idb) {
        this.idp = idb;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
