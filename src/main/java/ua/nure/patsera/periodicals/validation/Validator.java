package ua.nure.patsera.periodicals.validation;

/**
 * Created by Дарина on 11.09.2017.
 */
public class Validator {
    private static final String STRING_REGEX = "^[A-Z][a-z]+";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String PHONE_REGEX = "^[0][0-9]{9}";

    public static boolean isValidName (String string) {
        return string.matches(STRING_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidPhone(String phone) { return phone.matches(PHONE_REGEX); }
}

