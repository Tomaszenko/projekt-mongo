package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tomek on 09.05.2017.
 */

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;

    public User() {
        this.name = "Marek";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
