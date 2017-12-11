package com.example.emili.friendbox.data.model;

/**
 * Created by emili on 20/11/2017.
 */

public class User {

    private String ID;
    private String firstName;
    private String lastName;
    private String email;
    private String adress;
    private int postalCode;
    private long number;
    private boolean isDesabled;
    private boolean hasLink = false;
    
    public User(){

    }

    public User(String ID, String firstName, String lastName, String email, String adress, int postalCode, long number, boolean isDesabled, boolean hasLink){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.postalCode = postalCode;
        this.number = number;
        this.isDesabled = isDesabled;
        this.hasLink = hasLink;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public String getAdress(){
        return adress;
    }

    public int getPostalCode(){
        return postalCode;
    }

    public String getID(){
        return ID;
    }

    public long getNumber(){
        return number;
    }

    public String getEmail(){
        return email;
    }

    public boolean isHasLink(){
        return hasLink;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public boolean isDesabled(){
        return isDesabled;
    }
}
