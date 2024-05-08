package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;
    private List<User> userList;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    // private static final String usersPath = "app/src/main/java/org/example/localDB/Users.json";
    private static final String usersPath = "C:/Users/KIIT/Desktop/JAVA DEV/ticketBooking/app/src/main/java/org/example/localDB/Users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public UserBookingService(User user) throws IOException {
        this.user=user;
        loadUsers();
    }

    public void loadUsers() throws IOException {
        File users = new File(usersPath);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

//    public Boolean loginUser(){
//        Optional<User> foundUser = userList.stream().filter(ele -> {
//            return ele.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), ele.getHashedPassword());
//        }).findFirst();
//        return foundUser.isPresent();
//    }

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

    public void fetchBooking(){
        user.printTickets();
    }

    public void cancelBooking(String ticketID){
        //to-do
    }
}
