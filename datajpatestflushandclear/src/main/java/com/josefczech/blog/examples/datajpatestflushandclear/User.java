package com.josefczech.blog.examples.datajpatestflushandclear;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uc_name", columnNames = "name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Article> articles = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void addArticle(Article article) {
        articles.add(article);
        article.setAuthor(this);
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }
}
