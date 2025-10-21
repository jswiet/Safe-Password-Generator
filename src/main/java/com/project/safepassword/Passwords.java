package com.project.safepassword;

import java.util.Objects;


public class Passwords {
    private String password;

    public Passwords() {
    }

    public Passwords(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Passwords{" + "password='" + password + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passwords passwords = (Passwords) o;
        return Objects.equals(password, passwords.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
