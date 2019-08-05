package util;

import com.myproject.util.PasswordUtil;
import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilTest {

    @Test
    public void testingEncryptPassWithSaltWhenPassOnlyLetters() {
        String pass = "admin";
        String salt = "NMMP2FF9";
        String hashedAndSaledPass =
                "urxSH+vIouBZ8MaTHKJt15U3HtGHXs/WHW2ApdxF9jc=";
        Assert.assertEquals(PasswordUtil.encryptPassWithSalt(pass, salt), hashedAndSaledPass);
    }

    @Test
    public void testingEncryptPassWithSaltWhenPassHasSpecSymbols() {
        String pass = "___!!#$";
        String salt = "NMMP2FF9";
        String hashedAndSaledPass =
                "Rd+mUNi69ba2x7q+tB0HUqKoD3r6SyDJqm9vhBdXKxg=";
        Assert.assertEquals(PasswordUtil.encryptPassWithSalt(pass, salt), hashedAndSaledPass);
    }

    @Test
    public void testingEncryptPassWithSaltWhenPassHasOnlyNumbers() {
        String pass = "123456";
        String salt = "NMMP2FF9";
        String hashedAndSaledPass =
                "YosgwDcj6INeKEi26QqxHzvcRNkEupiLn0J+moX6CeA=";
        Assert.assertEquals(PasswordUtil.encryptPassWithSalt(pass, salt), hashedAndSaledPass);
    }

    @Test
    public void testingEncryptPassWithSaltWhenPassWithSpace() {
        String pass = "1asd 56";
        String salt = "NMMP2FF9";
        String hashedAndSaledPass =
                "Lpdf9jraQTIflZPV2UOvqvtKMC9ruHDN4aHxkpF1iUk=";
        Assert.assertEquals(PasswordUtil.encryptPassWithSalt(pass, salt), hashedAndSaledPass);
    }
}
