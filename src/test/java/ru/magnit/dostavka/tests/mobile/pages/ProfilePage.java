package ru.magnit.dostavka.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement profileIcon = $(AppiumBy.id("ru.magnit.express.android:id/item_profile"));
    private final SelenideElement titleText = $(AppiumBy.id("ru.magnit.express.android:id/titleTextView"));
    private final SelenideElement phoneEditText = $(AppiumBy.id("ru.magnit.express.android:id/phoneEditText"));
    private final SelenideElement phoneNumberError = $(AppiumBy.id("ru.magnit.express.android:id/textinput_error"));


    @Step("Click to profile icon")
    public ProfilePage clickToProfile() {
        profileIcon.click();
        return this;
    }

    @Step("Check profile page title")
    public  ProfilePage checkProfileTitle() {
        titleText.shouldHave(text("Введите номер телефона"));
        return this;
    }

    @Step("Enter phone Number")
    public  ProfilePage enterPhoneNumber(String number) {
        phoneEditText.click();
        phoneEditText.sendKeys(number);
        return this;
    }

    @Step("Check invalid phone number")
    public  ProfilePage checkInvalidNumber() {
        phoneNumberError.shouldHave(text("Введите корректный номер телефона"));
        return this;
    }

    @Step("Check valid phone number")
    public  ProfilePage checkValidNumber() {
        phoneNumberError.shouldNot(exist);
        return this;
    }




}
