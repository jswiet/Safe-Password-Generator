package com.project.safepassword;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PasswordGenerator {

    public String generatePassword(int length, boolean useNumbers, boolean useSpecialChar) {

        if (length <= 5 || length > 50) {
            throw new IllegalArgumentException("Length must be between 5 and 50!");
        }

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "@#$%&*!?";
        String output = letters;

        if (useNumbers) {
            output += digits;
        }

        if (useSpecialChar) {
            output += specialCharacters;
        }
        List<Character> passwordChars = new ArrayList<>();

        if (useNumbers) {
            passwordChars.add(digits.charAt((int) (
                    Math.random() *
                            digits.length())));
        }
        if (useSpecialChar) {
            passwordChars.add(specialCharacters.charAt((int) (
                    Math.random() *
                            specialCharacters.length())));
        }

        while (passwordChars.size() < length) {
            passwordChars.add(output.charAt((int) (
                    Math.random() *
                            output.length())));
        }

        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

}
