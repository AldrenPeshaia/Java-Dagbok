import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private static List<User> listOfUsers = new ArrayList<>();
    private static List<User> temp = new ArrayList<>();
    private static List<User> userListFromJson = new ArrayList<>();
    private static File file = new File("src/main/resources/users.json");



    /** metod för att visa listOfUsers*/
    public static void showListOfUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        file.createNewFile();
        userListFromJson = List.of(mapper.readValue(Paths.get("src/main/resources/users.json").toFile(), User[].class));

        for (User i : userListFromJson){
            System.out.println(i.getUserName());
        }
    }
    /** Metod för att lägga till users till listOfUsers*/
    public static void addToListOfUsers(User newUser) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //createNewFile() skapar en ny fil bara om det behövs
        file.createNewFile();
        Path paths = Paths.get("src/main/resources/users.json");

        try {
            temp = List.of(mapper.readValue(Paths.get("src/main/resources/users.json").toFile(), User[].class));
            List<User> newUserList = new ArrayList<>(temp);
            newUserList.add(newUser);
            mapper.writeValue(paths.toFile(), newUserList);
        } catch (MismatchedInputException e) {
            listOfUsers.add(newUser);
            mapper.writeValue(paths.toFile(), listOfUsers);
        }
    }

    /**Metod för att jämföra activeUser med listOfUsers */
    public static boolean findInListOfUsers(User activeUser) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        file.createNewFile();
        listOfUsers = List.of(mapper.readValue(Paths.get("src/main/resources/users.json").toFile(), User[].class));

        for (User listOfUser : listOfUsers) {
            if (listOfUser.getUserName().equals(activeUser.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /** Konstruktor */
    public User(String userName) {
        this.userName = userName;
    }
    /**en tom konsruktor*/
    public User() {
    }

    /** Getters och setters */
    public static List<User> getListOfUsers() {
        return listOfUsers;
    }

    public static void setListOfUsers(List<User> listOfUsers) {
        User.listOfUsers = listOfUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

