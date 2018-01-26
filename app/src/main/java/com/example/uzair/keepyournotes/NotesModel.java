package com.example.uzair.keepyournotes;

/**
 * Created by Uzair Raza on 1/25/2018.
 */

public class NotesModel {



    public NotesModel() {
    }

    public NotesModel(String name, String body, String date) {
        Name = name;
        Body = body;
        Date = date;
    }

    String Name;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String Body;
    String Date;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
