package com.example.dto;

import com.example.models.Commentary;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Tomek on 05.06.2017.
 */
public class FullEntryDTO {
    private String id;
    private String title;
    private String intro;
    private String text;
    private String category;
    private ArrayList<FullCommentaryDTO> commentaries;
    private String dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public ArrayList<FullCommentaryDTO> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(ArrayList<FullCommentaryDTO> commentaries) {
        this.commentaries = commentaries;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}