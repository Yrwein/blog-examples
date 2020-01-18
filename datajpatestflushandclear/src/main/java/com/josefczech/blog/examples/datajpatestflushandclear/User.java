package com.josefczech.blog.examples.datajpatestflushandclear;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uc_name", columnNames = "name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
