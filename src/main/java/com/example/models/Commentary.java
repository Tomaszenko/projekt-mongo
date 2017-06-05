package com.example.models;

import org.joda.time.DateTime;

/**
 * Created by Tomek on 05.06.2017.
 */
public class Commentary {
    private DateTime dateTime;
    private String nick;
    private String text;

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
