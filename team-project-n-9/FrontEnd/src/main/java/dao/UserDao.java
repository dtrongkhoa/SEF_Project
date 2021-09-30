package dao;

import model.User;
import paths.databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDao {
	private static String currentUserEmail;
	private static User currentUser;
    public static List<User> users = readDatabase();

    public static String getCurrentUserType() {
    	return currentUser.getAccountType();
    }
    public static User getCurrentUser(){
        return currentUser;
    }

    public static void logOut() {
    	currentUserEmail =null;
    	currentUser = null;
    }

    public static String getCurrentUserName(){
    	if(currentUserEmail!= null) {
    		return currentUserEmail;
    	}
    	return "Not logged in";
    }

    public static List<User> readDatabase(){

        List<User> users = new ArrayList<>();
        try {
            File myObj = new File(databases.ACCOUNTS);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(",");
                users.add(new User(dataArray[0], dataArray[1], dataArray[2],
                        dataArray[3], dataArray[4], dataArray[5], dataArray[6], dataArray[7]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return users;
    }

    public static boolean doesUserExist(String email, String pass){
    	boolean toReturn = false;
    	String toCheckEmail = email;
    	String toCheckPass = pass;
    	for (User user: users) {
    		if( user.getEmail().equals(toCheckEmail) && user.getPassword().equals(toCheckPass) ) {
    			currentUserEmail = toCheckEmail;
    			currentUser = user;
    			toReturn = true;
    		}
    	}
        return toReturn;
    }

    //updates the table
    public static void updateTable(){
        users = readDatabase();
    }

    public static void addUser(User u){
        final String DELIMITER = ",";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            sb.append(u.getEmail());
            sb.append(DELIMITER);
            sb.append(u.getPassword());
            sb.append(DELIMITER);
            sb.append(u.getFirstname());
            sb.append(DELIMITER);
            sb.append(u.getLastname());
            sb.append(DELIMITER);
            sb.append(u.getGender());
            sb.append(DELIMITER);
            sb.append(u.getDob());
            sb.append(DELIMITER);
            sb.append(u.getCountry());
            sb.append(DELIMITER);
            sb.append(u.getAccountType());
            Files.write(Paths.get(databases.ACCOUNTS), sb.toString().getBytes(), StandardOpenOption.APPEND);
            updateTable();
        }catch (IOException e) {
            System.err.println("User Database is not found");
        }
    }




}
