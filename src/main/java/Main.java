import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        String usersMenuChoice;
        int entryToRead;
        User tempUser = new User();
        String title;
        String entry;
        LocalDate date = LocalDate.now();
        User activeUser = new User("Ingen");

        do {
            for (String s : Menu.alternativ) {
                System.out.println("Välj nedanför alternativ från 1,2 eller 3 för att fortsätta med dagboken!!");

            }

            Menu.runFirstMenu(activeUser);
            usersMenuChoice = scString.nextLine();

            if (usersMenuChoice.equals("1")) {
                User.showListOfUsers();
                System.out.println("Vilken användare vill du fortsätta med?");
                tempUser.setUserName(scString.nextLine());

                if (User.findInListOfUsers(tempUser)) {
                    activeUser.setUserName(tempUser.getUserName());
                }
                else if (!User.findInListOfUsers(tempUser)){
                    System.out.println("Användare okänd");
                }

                while(!activeUser.getUserName().equalsIgnoreCase("Ingen")) {
                    Menu.runSecondMenu(activeUser);
                    usersMenuChoice = scString.nextLine();

                    if (usersMenuChoice.equals("1")) {
                        System.out.println("Dina inlägg");
                        System.out.println("-------------------");
                        Entries.listUsersEntries(activeUser);
                        System.out.println("Vilket inlägg vill du läsa?");

                        try {
                            entryToRead = scInt.nextInt();
                            Entries.selectEntry(entryToRead);

                        } catch (InputMismatchException e){
                            System.out.println("Skriv siffran för inlägget du vill läsa");
                            scInt.nextLine();
                        }

                    }
                    else if (usersMenuChoice.equals("2")) {
                        System.out.println("Skriv en titel:");
                        title = scString.nextLine();

                        System.out.println("Skriv inlägg här");
                        entry = scString.nextLine();

                        Entries newEntry = new Entries(activeUser, title, date.toString(), entry);
                        Entries.addEntry(newEntry);
                    }
                    else if (usersMenuChoice.equals("3")) {
                        System.out.println("Ses nästa gång " + activeUser.getUserName());
                        usersMenuChoice = "0";
                        activeUser.setUserName("ingen");
                    }
                    else {

                        System.out.println("Felaktig inmatning. Försök igen");
                    }
                }
            }

            else if (usersMenuChoice.equals("2")) {
                System.out.println("Skriv in namn på ny användare:");
                User newUser = new User(scString.nextLine());
                User.addToListOfUsers(newUser);

            }
            else if (usersMenuChoice.equals("3")) {
                System.out.println("Hej då!");
            }
            else {
                System.out.println("Felaktig inmatning. Försök igen");
            }
        } while(usersMenuChoice.equals("0") || !usersMenuChoice.equals("3"));

    }

}