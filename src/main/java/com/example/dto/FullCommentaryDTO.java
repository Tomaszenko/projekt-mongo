package com.example.dto;

/**
 * Created by Tomek on 08.06.2017.
 */
public class FullCommentaryDTO {
    private String text;
    private String nick;
    private String dateTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
