package com.example.dto;

import org.joda.time.DateTime;

/**
 * Created by Tomek on 07.06.2017.
 */
public class IntroEntryDTO {
    private String id;
    private String title;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
