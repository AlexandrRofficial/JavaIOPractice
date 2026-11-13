package JavaIOPractice;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserParser {
    public static final String RESOURCES = "src/main/resources/";

    public static void saveAllUsers(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RESOURCES + "user.json"))) {
            bufferedWriter.write(json);
            bufferedWriter.flush();
            System.out.println("All users successfully saved!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
