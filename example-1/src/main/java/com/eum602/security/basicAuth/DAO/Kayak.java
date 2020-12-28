package com.eum602.security.basicAuth.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@Data // Lombok: adds getters and setters
public class Kayak {

    public Kayak(String name, String owner, Number value, String makeModel) {
        this.name = name;
        this.owner = owner;
        this.value = value;
        this.makeModel = makeModel;
    }

    public Kayak() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String owner;

    private Number value;

    private String makeModel;
}
