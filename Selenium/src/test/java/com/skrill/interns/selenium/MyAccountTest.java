package com.skrill.interns.selenium;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyAccountTest {

    private WebDriver firefox;
    private static Random rand;

    @BeforeTest
    public void startTest() {
        rand = new Random();
        firefox = new FirefoxDriver();
    }

    @Test
    public void when_there_is_some_of_the_contries_which_are_not_supported_then_shows_invalid_country_label() throws Exception {
        // GIVEN
        firefox.get(Constants.MONEY_BOOKERS_PAGE);
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.LOGIN_PAGE_EMAIL_ID)));
        firefox.findElement(By.className(Constants.LOGIN_PAGE_SIGN_UP_BUTTON_CLASS)).click();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.SIGN_UP_PAGE1_COUNTRY_DROPBOX_ID)));
        // WHEN
        String[] countries = { "Nigeria", "Cuba", "Afghanistan", "Korea", "Sudan" };
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_COUNTRY_DROPBOX_INPUT_ID)).sendKeys(countries[rand.nextInt(4)]);
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_LANGUAGE_DROPBOX_INPUT_ID)).submit();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='controls']/span")));
        List<WebElement> labels = firefox.findElements(By.className(Constants.INVALID_VALUE_LABEL));
        // THEN
        assertTrue(labels.size() > 0);
    }

    @Test
    public void when_first_name_is_wrong_then_doesnt_go_to_the_next_page() throws Exception {
        // GIVEN
        firefox.get(Constants.MONEY_BOOKERS_PAGE);
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.LOGIN_PAGE_EMAIL_ID)));
        firefox.findElement(By.className(Constants.LOGIN_PAGE_SIGN_UP_BUTTON_CLASS)).click();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.SIGN_UP_PAGE1_COUNTRY_DROPBOX_ID)));
        // WHEN
        firefox.findElement(By.id(Constants.SIGN_UP_PAGE1_FIRST_NAME_FIELD_INPUT_ID)).sendKeys("1233");
        firefox.findElement(By.className(Constants.SIGN_UP_PAGE1_BUTTON_TO_NEXT_STEP_CLASS)).click();

        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='controls']/span")));
        List<WebElement> labels = firefox.findElements(By.className(Constants.INVALID_VALUE_LABEL));
        // THEN
        assertTrue(labels.size() > 0);
    }

    @Test
    public void the_user_can_register_himself() {
        // GIVEN
        User user1 = new User(firefox);
        // WHEN
        user1.register();
        // THEN
        assertTrue(Constants.SIGN_UP_PAGE.equals(firefox.getCurrentUrl()));
        user1.logOut();
    }

    @Test
    public void the_user_can_logIn_and_logOut() {
        // GIVEN
        firefox.get(Constants.LOGIN_PAGE);
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.LOGIN_PAGE_EMAIL_ID)));
        // WHEN
        firefox.findElement(By.id(Constants.LOGIN_PAGE_EMAIL_ID)).sendKeys("kvo_stava_be@gmail.com");
        WebElement button = firefox.findElement(By.id(Constants.LOGIN_PAGE_PASSWORD_ID));
        button.sendKeys("yavor1234");
        button.submit();
        firefox.findElement(By.className(Constants.LOGIN_PAGE_LOGIN_BUTTON_CLASS)).click();
        new WebDriverWait(firefox, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("clearfix")));
        // THEN
        assertTrue(Constants.MONEY_BOOKERS_PAGE.equals(firefox.getCurrentUrl()));

        firefox.findElement(By.className(Constants.LOGED_USER_LOGOUT_BUTTON_CLASS)).click();
        assertTrue(Constants.LOGIN_PAGE.equals(firefox.getCurrentUrl()));
    }

    @AfterTest
    public void endTest() {
        firefox.quit();
    }

}