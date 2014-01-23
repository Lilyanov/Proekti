package com.skrill.interns.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Example {

    /**
     * @param args
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WebDriver firefox = new FirefoxDriver();
        User user1 = new User(firefox);
        user1.register();
        user1.logOut();
        user1.logIn();
        user1.logOut();
        firefox.close();

    }

}
