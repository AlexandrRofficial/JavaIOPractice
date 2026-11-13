package JavaIOPractice;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhoneNumberValidator {
    private static final String PHONE_REGEX = "^\\(?\\d{3}\\)?[-\\s]\\d{3}-\\d{4}$";

    public static void validatePhoneNumber(String number) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(number);
        boolean matchFound = matcher.find();

        if (matchFound) {
            System.out.println(number);
        }
    }
}
