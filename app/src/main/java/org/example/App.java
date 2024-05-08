/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.util.UserServiceUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {

        System.out.println("Welcome to train ticket booking system");
        Scanner sc = new Scanner(System.in);
        int option=0;
        UserBookingService userBookingService;

        try{
            userBookingService = new UserBookingService();
        }
        catch(IOException e){
            System.out.println(e);
            return;
        }

        while(option!=7){
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = sc.nextInt();

            switch(option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = sc.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = sc.next();
                    User userToSignup = new User(UUID.randomUUID().toString(),nameToSignUp, UserServiceUtil.hashedPassword(passwordToSignUp),new ArrayList<>());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = sc.next();
                    System.out.println("Enter the password to signup");
                    String passwordToLogin = sc.next();
                    User userToLogin = new User(UUID.randomUUID().toString(),nameToLogin, UserServiceUtil.hashedPassword(passwordToLogin),new ArrayList<>());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBooking();
                    break;
                default:
                    break;

            }
        }
    }
}
