package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public Author(String id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Author(){}

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
