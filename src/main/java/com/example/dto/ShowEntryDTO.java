package com.example.dto;

import com.example.models.Commentary;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Tomek on 05.06.2017.
 */
public class ShowEntryDTO {
    private String id;
    private String title;
    private String text;
    private String shorterText;
    private ArrayList<String> tags;
    private ArrayList<Commentary> commentaries;
    private String dateTime;

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

    public String getShorterText() {
        return shorterText;
    }

    public void setShorterText(String shorterText) {
        this.shorterText = shorterText;
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

    public ArrayList<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(ArrayList<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}