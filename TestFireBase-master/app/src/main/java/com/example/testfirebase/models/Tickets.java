package com.example.testfirebase.models;

import java.util.Date;

public class Tickets {

    private String id;
    private String auteurId;
    private String titre;
    private Date date;
    private String image;
    private String lieu;

    public Tickets(String id, String auteurId,String titre,Date date, String image,String lieu)
    {
        this.auteurId = auteurId;
        this.date = date;
        this.id = id;
        this.image=image;
        this.lieu = lieu;
        this.titre=titre;
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

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getLieu() {
        return lieu;
    }

    public String toString() {

        return titre;
    }


}
