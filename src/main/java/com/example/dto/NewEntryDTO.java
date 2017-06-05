package com.example.dto;

import java.util.ArrayList;

/**
 * Created by Tomek on 05.06.2017.
 */
public class NewEntryDTO {
    private String title;
    private String text;
    private ArrayList<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
