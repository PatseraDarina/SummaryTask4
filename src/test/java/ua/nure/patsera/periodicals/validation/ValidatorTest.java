package ua.nure.patsera.periodicals.validation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Дарина on 24.09.2017.
 */
public class ValidatorTest {

    @Test
    public void emailShouldCorrespondPattern() throws Exception {
        //Given
        String email = "qwertyui123";

        //When
        boolean isMatch;
        isMatch = Validator.isValidEmail(email);

        //Then
        Assert.assertFalse(isMatch);
    }

    @Test
    public void passwordShouldCorrespondPattern() throws Exception {
        //Given
        String pswd = "123";

        //When
        boolean isMatch;
        isMatch = Validator.isValidPassword(pswd);

        //Then
        Assert.assertFalse(isMatch);
    }

    @Test
    public void phoneShouldCorrespondPattern() throws Exception {
        //Given
        String phone = "89074";

        //When
        boolean isMatch;
        isMatch = Validator.isValidPassword(phone);

        //Then
        Assert.assertFalse(isMatch);
    }
}