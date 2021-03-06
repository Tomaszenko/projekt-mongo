package com.example.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by Tomek on 09.05.2017.
 */

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotNull(message = "Imię jest wymagane")
    @Size(min = 1)
    private String first_name;

    @NotNull(message = "Nazwisko jest wymagane")
    @Size(min = 1)
    private String last_name;

    @Size(max=200)
    private String description;

    private ArrayList<String> hobbies;

//    private String imgPath;

    @Email
    private String contact;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

//    public String getImgPath() {
//        return imgPath;
//    }
//
//    public void setImgPath(String imgPath) {
//        this.imgPath = imgPath;
//    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
