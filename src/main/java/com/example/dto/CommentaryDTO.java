package com.example.dto;

import java.util.Date;

/**
 * Created by Tomek on 05.06.2017.
 */
public class CommentaryDTO {
    private String text;
    private String nick;

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
}
