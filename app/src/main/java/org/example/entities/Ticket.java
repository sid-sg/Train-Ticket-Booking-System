package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String ticketID;
    private String userID;
    private String source;
    private String destination;
    private String dateOfTravel;
    private Train train;

    public Ticket(){}

    public Ticket(String ticketID, String userID, String source, String destination, String dateOfTravel, Train train) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    // Getters
    public String getTicketID() {
        return ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketID, userID, source, destination, dateOfTravel);
    }

    // Setters
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setTrain(Train train) {
        this.train = train;
    }



}
