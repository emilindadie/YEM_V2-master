package com.example.emili.friendbox.data.model;

/**
 * Created by emili on 20/11/2017.
 */

public class Pictogramme {

    String nom;
    String message;
    int image;
    int audioPictogramme;


    public Pictogramme(String nom, String message, int image, int audioPictogramme){
        this.nom = nom;
        this.message = message;
        this.image = image;
        this.audioPictogramme = audioPictogramme;
    }

    public int getImage(){
        return image;
    }

    public String getNom(){
        return nom;
    }
}
