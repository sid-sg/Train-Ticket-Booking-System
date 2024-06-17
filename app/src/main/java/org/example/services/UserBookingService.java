package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class UserBookingService {

    private User user;
    private TrainService trainService = new TrainService();
    private List<User> userList;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String ticketSource;
    private String ticketDestination;
    private String date;

    
     private static final String usersPath = "app/src/main/java/org/example/localDB/Users.json";
//    private static final String usersPath = "C:/Users/KIIT/Desktop/JAVA DEV/ticketBooking/app/src/main/java/org/example/localDB/Users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public UserBookingService(User user) throws IOException {
        this.user=user;
        loadUsers();
    }

    public UserBookingService(Optional<User> foundUser) throws IOException {}

    public void loadUsers() throws IOException {
        File users = new File(usersPath);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser() throws IOException {
        System.out.println(user.getUsername());
        System.out.println(user.getPlainPassword());
        Optional<User> foundUser = userList.stream().filter(ele -> {
            return ele.getUsername().equals(user.getUsername()) && UserServiceUtil.checkPassword(user.getPlainPassword(), ele.getHashedPassword());
        }).findFirst();
        if(foundUser.isPresent()){
            UserBookingService ub = new UserBookingService(foundUser);
        }
        return foundUser.isPresent();
    }

    public void signUp(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            this.user = user;
        }catch (IOException ex){
            System.out.println("There is something wrong");
        }
    }

    public void updateUserInfo(User user){
        OptionalInt index = IntStream.range(0,userList.size())
                .filter( i -> userList.get(i).getUsername().equals(user.getUsername())).findFirst();
        if(index.isPresent()){
            User foundUser = userList.get(index.getAsInt());
            this.user = foundUser;
        }
        else{
            System.out.println("Something went wrong");
        }
    }

    public void saveUserListToFile() throws IOException {
        File users = new File(usersPath);
        objectMapper.writeValue(users, userList);
    }

    public void updateUserListToFile(User user) throws IOException{
        loadUsers();
        OptionalInt index = IntStream.range(0,userList.size())
                .filter( i -> userList.get(i).getUserID().equals( user.getUserID() )).findFirst();

        if(index.isPresent()){
            userList.set(index.getAsInt(), user);
            saveUserListToFile();
        }
        else{
            System.out.println("Something went wrong");
        }
    }

    public List<Train> getTrains(String source, String destination) throws IOException {
        ticketSource = source;
        ticketDestination = destination;
        TrainService trainService =  new TrainService();
        return trainService.searchTrains(source, destination);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public static List<List<Integer>> fetchSeats(Train train){
        List<List<Integer>> seats = train.getSeats();
        return seats;
    }

    public Boolean bookSeat (Train train, Integer row, Integer col) throws IOException {
        List<List<Integer>> trainSeats = train.getSeats();
        if(row <= 0 || col <= 0  || row > trainSeats.size() || col > trainSeats.get(0).size() ){
            System.out.println("Enter valid row and column");
            return false;
        }
        if(trainSeats.get(row - 1).get(col - 1) == 1){
            System.out.println("Seat already booked");
            return false;
        }

        trainSeats.get(row - 1).set(col - 1, 1);
        train.setSeats(trainSeats);
        trainService.addTrain(train);
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), user.getUsername(), ticketSource, ticketDestination, date , train);

        List<Ticket> ticketsBooked = user.getTicketsBooked();
        if (ticketsBooked == null) {
            ticketsBooked = new ArrayList<>();
        }

        ticketsBooked.add(ticket);
        user.setTicketsBooked(ticketsBooked);
        updateUserListToFile(user);

        return true;
    }

    public Boolean cancelBooking(String ticketID){
        OptionalInt index = IntStream.range(0, userList.size())
                .filter( i -> userList.get(i).getUsername().equals(user.getUsername())).findFirst();
        if(index.isPresent()){

            User newUser = userList.get(index.getAsInt());
            List<Ticket> tickets = newUser.getTicketsBooked();

            OptionalInt idx = IntStream.range(0, tickets.size())
                    .filter( j -> tickets.get(j).getTicketID().equals(ticketID)).findFirst();

            if(idx.isPresent()){
                tickets.remove(idx.getAsInt());
                newUser.setTicketsBooked(tickets);
                this.user = newUser;
                updateUserInfo(newUser);

            }
        }
        else{
            System.out.println("Something went wrong");
            return false;
        }
        return true;
    }

    public void setDate(String date){
        this.date = date;
    }
}
