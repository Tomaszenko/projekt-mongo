package com.example.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Created by Tomek on 05.06.2017.
 */

@Document(collection = "entries")
public class Entry {
    @Id
    private String id;

    private DateTime dateTime;
    private String title;
    private String text;
    private String category;
    private ArrayList<Commentary> commentaries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
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

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(ArrayList<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
}
