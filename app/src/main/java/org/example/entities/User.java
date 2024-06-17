package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String userID;
    private String username;
    private String firstName;
    private String lastName;
    private String plainPassword;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;

    public User(String userID, String username, String firstName, String lastName, String plainPassword, String hashedPassword, List<Ticket> ticketsBooked){
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.plainPassword = plainPassword;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
    }

    public User(String username,String plainPassword ,String hashedPassword){
        this.username = username;
        this.plainPassword = plainPassword;
        this.hashedPassword = hashedPassword;
    }

    public User(){}

    // Getters
    public String getUserID(){
        return this.userID;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public String getPlainPassword(){
        return this.plainPassword;
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

    public void setUsername(String username){
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPlainPassword(String plainPassword){
        this.plainPassword = plainPassword;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

    public void printTickets(){
        if(ticketsBooked == null || ticketsBooked.isEmpty()){
            System.out.println("No tickets booked");
            return;
        }

        for (Ticket ticket : ticketsBooked) {
            System.out.println(ticket.getTicketInfo());
        }
    }
}
