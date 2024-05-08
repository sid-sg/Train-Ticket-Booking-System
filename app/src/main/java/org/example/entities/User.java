package org.example.entities;

import java.util.List;

public class User {
    private String userID;
    private String name;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;

    public User(String userID, String name, String hashedPassword, List<Ticket> ticketsBooked){
        this.userID=userID;
        this.name=name;
        this.hashedPassword=hashedPassword;
        this.ticketsBooked=ticketsBooked;
    }

    public User(){}

    // Getters

    public String getUserID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }

    public String getHashedPassword(){
        return this.hashedPassword;
    }

    public List<Ticket> getTicketsBooked(){
        return this.ticketsBooked;
    }

    // Setters

    public void setUserID(String userID){
        this.userID = userID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

    public void printTickets(){
        for(int i=0;i<ticketsBooked.size();i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }


}

