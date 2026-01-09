package com.project.safepassword;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordGeneratorUnitTests {
    private final PasswordGenerator UTPasswordGenerator = new PasswordGenerator();

    @Test
    void canGenerateRandomPasswords() {
        //when
        var password_1 = UTPasswordGenerator.generatePassword(10, false, false);
        var password_2 = UTPasswordGenerator.generatePassword(10, false, false);
        //then
        assertThat(password_1).isNotEqualTo(password_2);
    }

    @Test
    void canGeneratePasswordWithWantedLength() {
        //when
        var password_1 = UTPasswordGenerator.generatePassword(12, false, false);
        var password_2 = UTPasswordGenerator.generatePassword(12, true, true);
        var password_3 = UTPasswordGenerator.generatePassword(5, false, true);
        var password_4 = UTPasswordGenerator.generatePassword(50, false, true);
        //then
        assertThat(password_1.length()).isEqualTo(12);
        assertThat(password_2.length()).isEqualTo(12);
        assertThat(password_3.length()).isEqualTo(5);
        assertThat(password_4.length()).isEqualTo(50);
    }

    @Test
    void canGeneratePasswordWithoutNumbersWithoutSpecialCharacters() {
        //when
        var password = UTPasswordGenerator.generatePassword(8, false, false);
        //then
        assertThat(password.matches("^[a-zA-Z]+$")).isEqualTo(true);
    }

    @Test
    void canGeneratePasswordWithNumbersWithoutSpecialCharacters() {
        //when
        var password = UTPasswordGenerator.generatePassword(8, true, false);
        //then
        assertThat(password.matches(".*[0-9].*")).isEqualTo(true);
    }

    @Test
    void canGeneratePasswordWithoutNumbersWithSpecialCharacters() {
        //when
        var password = UTPasswordGenerator.generatePassword(8, false, true);
        //then
        assertThat(password.matches(".*[@#$%&*!?].*")).isEqualTo(true);
    }

    @Test
    void canGeneratePasswordWithNumbersWithSpecialCharacters() {
        //when
        var password = UTPasswordGenerator.generatePassword(8, true, true);
        //then
        assertThat(password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%&*?]).+$")).isEqualTo(true);
    }

    @Test
    void willFailWhenLengthIsBelowFiveOrAbove50() {

        assertThatThrownBy(() -> UTPasswordGenerator.generatePassword(4, false, true)).isInstanceOf(IllegalArgumentException.class)
                                                                                      .hasMessage("Length must be between 5 and 50!");

        assertThatThrownBy(() -> UTPasswordGenerator.generatePassword(-10, false, true)).isInstanceOf(IllegalArgumentException.class)
                                                                                        .hasMessage("Length must be between 5 and 50!");

        assertThatThrownBy(() -> UTPasswordGenerator.generatePassword(51, false, true)).isInstanceOf(IllegalArgumentException.class)
                                                                                       .hasMessage("Length must be between 5 and 50!");

        assertThatThrownBy(() -> UTPasswordGenerator.generatePassword(100, false, true)).isInstanceOf(IllegalArgumentException.class)
                                                                                        .hasMessage("Length must be between 5 and 50!");
    }

}