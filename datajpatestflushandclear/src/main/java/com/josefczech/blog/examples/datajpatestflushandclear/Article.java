package com.josefczech.blog.examples.datajpatestflushandclear;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column(name = "content")
    private String content;

    public Article() {
    }

    public Article(String content) {
        this.content = content;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
