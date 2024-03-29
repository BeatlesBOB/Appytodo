package com.example.testfirebase.models;

import java.util.Date;

public class Tickets {

    private String id;
    private String auteurId;
    private String titre;
    private String date;
    private String image;
    private String lieu;
    private String desc;

    public Tickets(String id, String auteurId,String titre,String date, String image,String lieu, String desc)
    {
        this.auteurId = auteurId;
        this.date = date;
        this.id = id;
        this.image=image;
        this.lieu = lieu;
        this.titre=titre;
        this.desc=desc;
    }

    public String getId() {
        return id;
    }

    public String getAuteurId() {
        return auteurId;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getLieu() {
        return lieu;
    }

    public String toString() {
        return titre;
    }


}
