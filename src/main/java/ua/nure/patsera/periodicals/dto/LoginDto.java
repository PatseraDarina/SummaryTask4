package ua.nure.patsera.periodicals.dto;

/**
 * Created by Дарина on 11.09.2017.
 */
public class LoginDto {
    private String email;
    private String password;

    public LoginDto() {
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
