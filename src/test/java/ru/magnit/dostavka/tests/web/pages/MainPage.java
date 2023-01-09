package ru.dostavka.magnit.web.pages;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.SelenideElement;
import config.App;
import config.Project;
import io.qameta.allure.Step;
import ru.dostavka.magnit.domain.HeaderSectionLinks;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    private final SelenideElement magnitLogo = $(".m-header-logo__image");
    private final SelenideElement phoneNumber = $(".m-navigation__phone");
    private final SelenideElement headerSectionLink = $(by("data-test-id", "bottom-header-section"));
    private final SelenideElement loginIcon = $(by("data-test-id", "login-icon"));
    private final SelenideElement loginPopup = $(by("data-test-id", "login-popup"));
    private final SelenideElement emailLoginBtn = $(by("data-test-id", "email-login-btn"));
    private final SelenideElement inputEmail = $("input[data-test-id=\"input-email\"]");
    private final SelenideElement inputPassword = $("input[data-test-id=\"input-password\"]");
    private final SelenideElement loginButton = $(by("data-test-id", "login-btn"));

    @Step("Click on bookmate header logo")
    public MainPage clickOnMagnitLogo() {
        magnitLogo.click();
        return this;
    }

    @Step("Verify page {url} is open")
    public void checkPageIsOpen(String url) {
        webdriver().shouldHave(url(url));
    }

    @Step("Open header bottom section link: {headerSectionLinks.name}")
    public MainPage openHeaderCatItem(HeaderSectionLinks headerSectionLinks) {
        headerSectionLink.$(withText(headerSectionLinks.name)).click();
        return this;
    }

    @Step("Checking the phone number {number}")
    public void checkPhoneNumber(String number) {
        phoneNumber.shouldHave(text(number));
    }

    @Step("Click login button")
    public MainPage clickLoginIcon() {
        loginIcon.click();
        return this;
    }


    @Step("Login Popup should be visible")
    public MainPage checkLoginPopupIsOpen() {
        loginPopup.shouldBe(visible).shouldHave(text("Войти в личный кабинет"));
        return this;
    }

    @Step("Logging with email:{email} and password:{password}")
    public MainPage login(String email, String password) {
        loginIcon.click();
        emailLoginBtn.click();
        inputEmail.setValue(email);
        inputPassword.setValue(password);
        loginButton.click();
        return this;
    }


}
