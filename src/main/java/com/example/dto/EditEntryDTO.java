package com.example.dto;

import java.util.ArrayList;

/**
 * Created by Tomek on 07.06.2017.
 */
public class EditEntryDTO {
    private String id;
    private String title;
    private String text;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) { this.text = text; }

    public void setCategory(String category) { this.category = category; }

    public String getCategory() { return category; }
}
