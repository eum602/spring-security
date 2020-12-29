package com.eum602.security.basicAuth.DAO;

import lombok.Data;

import javax.persistence.*;

/**
 * Only JPA responsibility
 */
@Entity //specifies that the class is an entity and is mapped to a database table // This tells Hibernate to make a table out of this class
@Data
@Table(name="users")
public class User {
    public User() {
    }

    public User(String username, String password, String authority,int enabled) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.enabled = enabled;
    }

    @Id //specifies the primary key of an entity
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String authority;
    private int enabled;
}
