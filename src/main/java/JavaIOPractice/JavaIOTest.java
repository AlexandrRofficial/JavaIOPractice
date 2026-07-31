package JavaIOPractice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class JavaIOTest {
    public static void IOExceptionMessage(IOException e) {
        System.out.println("IOException Occured");
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    public static void validate(String number) {
        int length = number.length();
        if (length == 12) {
            if (Character.isDigit(number.charAt(0)) && Character.isDigit(number.charAt(1))
                    && Character.isDigit(number.charAt(2)) && number.charAt(3) == '-'
                    && Character.isDigit(number.charAt(4)) && Character.isDigit(number.charAt(5))
                    && Character.isDigit(number.charAt(6)) && number.charAt(7) == '-'
                    && Character.isDigit(number.charAt(8)) && Character.isDigit(number.charAt(9))
                    && Character.isDigit(number.charAt(10)) && Character.isDigit(number.charAt(11))) {
                System.out.println(number);
            }
        }
        else if (length == 14) {
            if (number.charAt(0) == '(' && Character.isDigit(number.charAt(1))
                    && Character.isDigit(number.charAt(2)) && Character.isDigit(number.charAt(3))
                    && number.charAt(4) == ')' && number.charAt(5) == ' '
                    && Character.isDigit(number.charAt(6)) && Character.isDigit(number.charAt(7))
                    && Character.isDigit(number.charAt(8)) && number.charAt(9) == '-'
                    && Character.isDigit(number.charAt(10)) && Character.isDigit(number.charAt(11))
                    && Character.isDigit(number.charAt(12)) && Character.isDigit(number.charAt(13))) {
                System.out.println(number);
            }
        }
        else {
            return;
        }
    }

    public static void main(String[] args) {
        System.out.println("--------------------");

        String inputFile = "src/main/resources/file.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while((line = bufferedReader.readLine()) != null) {
                validate(line);
            }
        } catch (IOException e) {
            IOExceptionMessage(e);
        }

        System.out.println("--------------------");

        String usersFile = "src/main/resources/users.txt";

        String jsonFile = "src/main/resources/user.json";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(usersFile))) {
            String line;

            bufferedReader.readLine();

            List<User> users = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                if (parts.length != 2) {
                    continue;
                }

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);

                User user = new User(name, age);

                users.add(user);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(users);

            System.out.println(json);

            try (FileWriter fileWriter = new FileWriter(jsonFile)) {
                fileWriter.write(json);
            } catch (IOException e) {
                IOExceptionMessage(e);
            }

        } catch(IOException e) {
            IOExceptionMessage(e);
        }

        System.out.println("--------------------");

        String wordsFile = "src/main/resources/words.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(wordsFile))) {
            String line;
            Map<String, Integer> frequency = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (int i = 0; i < words.length; i++) {
                    if (frequency.getOrDefault(words[i], 0) == 0) {
                        frequency.put(words[i], 1);
                    }
                    else {
                        int count = frequency.get(words[i]);
                        frequency.put(words[i], count + 1);
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException e) {
            IOExceptionMessage(e);
        }

        System.out.println("--------------------");
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
