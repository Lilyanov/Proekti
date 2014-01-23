package com.skrill.interns.selenium;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User {
    private static final String ALPHABET = "abcdefghijklmnoprqstvwxyzABCDEFGHIJKLMNOPQRSTVWXYZ1234567890._";
    private static Random rand = new Random();
    private WebDriver firefox;
    private String email, password;
    private boolean isLogin, isRegistred;

    private String choose_random_value_from_drop_down_menu(String id) {
        WebElement dropDownMenu = firefox.findElement(By.id(id));

        Select menu = new Select(dropDownMenu);
        List<WebElement> countries = menu.getOptions();

        int randomNumber = rand.nextInt(countries.size() - 1);
        String fieldValue = countries.get(randomNumber).getText();
        menu.selectByIndex(randomNumber);
        // dropDownMenu.sendKeys(fieldValue);
        // dropDownMenu.submit();

        return fieldValue;
    }

    private StringBuilder generateRandomString(int length) {
        StringBuilder result = new StringBuilder();
        int randIndex = 0;
        char c = '\0';
        for (int i = 0; i < length; i++) {
            randIndex = rand.nextInt(ALPHABET.length());
            c = ALPHABET.charAt(randIndex);
            result.append(c);
        }
        return result;
    }

    public User(WebDriver firefox) {
        this.firefox = firefox;
        email = password = "";
        isLogin = isRegistred = false;
    }

    public void register() {
        // connect to money bookers and loading sign up page
        firefox.get(Constants.MONEY_BOOKERS_PAGE);
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.LOGIN_PAGE_EMAIL_ID)));
        firefox.findElement(By.className(Constants.LOGIN_PAGE_SIGN_UP_BUTTON_CLASS)).click();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.SIGN_UP_PAGE1_COUNTRY_DROPBOX_ID)));
        // insert information for first sign up page
        choose_random_value_from_drop_down_menu(Constants.SIGN_UP_PAGE1_COUNTRY_DROPBOX_INPUT_ID);
        choose_random_value_from_drop_down_menu(Constants.SIGN_UP_PAGE1_CURRENCY_DROPBOX_INPUT_ID);
        choose_random_value_from_drop_down_menu(Constants.SIGN_UP_PAGE1_LANGUAGE_DROPBOX_INPUT_ID);
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.SIGN_UP_PAGE1_FIRST_NAME_FIELD_INPUT_ID)));

        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_FIRST_NAME_FIELD_INPUT_ID)).sendKeys(Constants.FIRST_NAME);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_LAST_NAME_FIELD_INPUT_ID)).sendKeys(Constants.LAST_NAME);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_ADDRES1_FIELD_INPUT_ID)).sendKeys(Constants.ADDRESS);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_CITY_FIELD_INPUT_ID)).sendKeys(Constants.CITY);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_POSTAL_CODE_FIELD_INPUT_ID)).sendKeys(Constants.POSTCODE);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_PHONE_TYPE_FIELD_INPUT_ID)).sendKeys(Constants.PHONE_TYPE);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_PHONE_FIELD_INPUT_ID)).sendKeys(Constants.PHONE_NUMBER);
        firefox.findElement(By.className(Constants.SIGN_UP_PAGE1_BUTTON_TO_NEXT_STEP_CLASS)).click();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.SIGN_UP_PAGE2_EMAIL_ID)));
        // insert information for second sign up page
        email = generateRandomString(10).append("@gmail.com").toString();
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_EMAIL_ID)).sendKeys(email);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_DAY_OF_BIRTH_ID)).sendKeys("2");
        choose_random_value_from_drop_down_menu(Constants.SIGN_UP_PAGE2_MONTH_OF_BIRTH_ID);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_YEAR_OF_BIRTH_ID)).sendKeys("1991");
        password = generateRandomString(10).append("1a").toString();
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_PASSWORD_INPUT_ID)).sendKeys(password);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_CONFIRM_PASSWORD_INPUT_ID)).sendKeys(password);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE2_CODE_INPUT_ID)).sendKeys(Constants.VERIFY_CODE);
        firefox.findElement(By.className(Constants.SIGN_UP_PAGE2_BUTTON_FOR_FINISH_REGISTRATION)).click();
        isRegistred = true;
        System.out.println("The User has been registred!");
    }

    public void logIn() {
        // connect to money bookers and log in
        if (isRegistred) {
            firefox.get(Constants.MONEY_BOOKERS_PAGE);
            new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.LOGIN_PAGE_EMAIL_ID)));
            firefox.findElement(By.id(Constants.LOGIN_PAGE_EMAIL_ID)).sendKeys(email);
            WebElement button = firefox.findElement(By.id(Constants.LOGIN_PAGE_PASSWORD_ID));
            button.sendKeys(password);
            button.submit();
            firefox.findElement(By.className(Constants.LOGIN_PAGE_LOGIN_BUTTON_CLASS)).click();
            new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("clearfix")));
            isLogin = true;
            System.out.println("The User is loged in");
        } else {
            System.out.println("The User is not registred");
        }
    }

    public void logOut() {
        if (isLogin || isRegistred) {
            firefox.get(Constants.MONEY_BOOKERS_PAGE);
            new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("clearfix")));
            firefox.findElement(By.className(Constants.LOGED_USER_LOGOUT_BUTTON_CLASS)).click();
            System.out.println("User is loged out");
            isLogin = false;
        } else {
            System.out.println("User is not loged in");
        }
    }

}
