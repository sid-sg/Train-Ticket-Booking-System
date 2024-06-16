package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;
    private List<User> userList;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    
//     private static final String usersPath = "app/src/main/java/org/example/localDB/Users.json";
    private static final String usersPath = "C:/Users/KIIT/Desktop/JAVA DEV/ticketBooking/app/src/main/java/org/example/localDB/Users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public UserBookingService(User user) throws IOException {
        this.user=user;
        loadUsers();
    }

    public UserBookingService(Optional<User> foundUser) {}

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
        }catch (IOException ex){
            System.out.println("There is something wrong");
        }
    }

    public void saveUserListToFile() throws IOException {
        File users = new File(usersPath);
        objectMapper.writeValue(users, userList);
    }

    public List<Train> getTrains(String source, String destination) throws IOException {
        TrainService trainService =  new TrainService();
        return trainService.searchTrains(source, destination);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public void cancelBooking(String ticketID){
        //to-do
    }
}
