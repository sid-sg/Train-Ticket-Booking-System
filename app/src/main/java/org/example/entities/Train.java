package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Train {
    private String trainID;
    private Integer trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes; //maps stations to time
    private List<String> stations;

    public Train(){}

    public Train(String trainID, Integer trainNo, List<List<Integer>> seats, Map<String,String> stationTimes, List<String> stations){
        this.trainID = trainID;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    // Getters
    public String getTrainID() {
        return trainID;
    }

    public Integer getTrainNo() {
        return trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }

    // Setters
    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public void setTrainNo(Integer trainNo) {
        this.trainNo = trainNo;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }


    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainID, trainNo);
    }
}
