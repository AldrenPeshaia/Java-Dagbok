import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Entries {

    private User user;
    private String title;
    private String entry;
    private String date;

    private static List<Entries> temporary = new ArrayList<>();
    private static List<Entries> listOfEntries = new ArrayList<>();
    private static List<Entries> tempEntries = new ArrayList<>();

    public static void addEntry (Entries newEntry) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File entryFile = new File("src/main/resources/entries.json");
        entryFile.createNewFile();

        Path path = Paths.get("src/main/resources/entries.json");

        try {
            temporary = List.of(mapper.readValue(Paths.get("src/main/resources/entries.json").toFile(),
                    Entries[].class));

            List<Entries> newListForEntries = new ArrayList<>(temporary);
            newListForEntries.add(newEntry);
            mapper.writeValue(path.toFile(),newListForEntries);

        } catch(MismatchedInputException e) {
            listOfEntries.add(newEntry);
            mapper.writeValue(path.toFile(), listOfEntries);
        }
    }

    public static void listUsersEntries (User activeUser) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File entryFile = new File("src/main/resources/entries.json");
        entryFile.createNewFile();
        int index = 1;

        listOfEntries = List.of(mapper.readValue(Paths.get("src/main/resources/entries.json").toFile(),
                Entries[].class));
        for (Entries e : listOfEntries){
            if (e.getUser().getUserName().equals(activeUser.getUserName())){
                System.out.println(index + ". " + e.getTitle());
                tempEntries.add(e);
                index++;
            }
        }

    }
    public static void selectEntry (int entryToRead){
        if (entryToRead <= tempEntries.size() && entryToRead > 0) {
            System.out.println(tempEntries.get(entryToRead - 1).getTitle());
            System.out.println(tempEntries.get(entryToRead - 1).getDate());
            System.out.println(tempEntries.get(entryToRead - 1).getEntry());
        } else {
            System.out.println("inlägget finns inte");
        }

        tempEntries.clear();


    }
    public Entries(User user, String title, String date, String entry) {
        this.user = user;
        this.title = title;
        this.date = date;
        this.entry = entry;
    }

    public Entries() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

