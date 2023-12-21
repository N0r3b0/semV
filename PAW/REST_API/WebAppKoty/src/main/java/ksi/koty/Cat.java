package ksi.koty;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cat {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String breed;

    public Cat() {

    }
    public Cat(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.breed);

    }
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Cat))
            return false;
        Cat cat = (Cat) object;
        return Objects.equals(this.id, cat.id) && Objects.equals(this.name, cat.name)
                && Objects.equals(this.breed, cat.breed);
    }

    @Override
    public String toString() {
        return "Cat{" + "id=" + this.id + ", name='" + this.name + '\'' + ", breed='" + this.breed + '\'' + '}';
    }
}
