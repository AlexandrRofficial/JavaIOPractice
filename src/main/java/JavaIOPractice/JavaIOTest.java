package JavaIOPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class JavaIOTest {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        System.out.println("--------------------");

        System.out.println("Phone Number Validator\n");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RESOURCES + "file.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                PhoneNumberValidator.validatePhoneNumber(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n--------------------");

        System.out.println("User Parser\n");

        List<User> userList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RESOURCES + "users.txt"))) {
            String line;
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split("\\s");
                String name = userData[0];
                Integer age = Integer.parseInt(userData[1]);
                userList.add(new User(name, age));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserParser.saveAllUsers(userList);

        System.out.println("\n--------------------");

        System.out.println("Word Frequency Counter\n");

        WordFrequencyCounter.countWordFrequency(RESOURCES + "words.txt");

        System.out.println("\n--------------------");
    }
}
