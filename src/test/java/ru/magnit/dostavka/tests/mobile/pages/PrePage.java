package ru.magnit.dostavka.tests.mobile.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import ru.magnit.dostavka.tests.web.pages.MainPage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class PrePage {
    private final SelenideElement skipButton = $(AppiumBy.xpath("//android.view.View[@content-desc=\"Пропустить\"]"));
    private final SelenideElement geoLocationButton = $(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
    private final SelenideElement valueEditText = $(AppiumBy.id("ru.magnit.express.android:id/valueEditText"));
    private final SelenideElement adressTextArea = $(byAttribute("text", "Адрес"));
    private final SelenideElement desiredAdress = $(byAttribute("text", "Ростов-на-Дону"));
    private final SelenideElement nextButton = $(byAttribute("text", "Далее"));



    @Step("Click the skip button")
    public PrePage clickOnSkipButton() {
        skipButton.click();
        return this;
    }

    @Step("Click the allow geolocation button.")
    public PrePage clickGeoLocationButton() {
        geoLocationButton.click();
        return this;
    }

    @Step("Click on the text edit area")
    public PrePage valueEditTextClick() {
        valueEditText.click();
        return this;
    }

    @Step("Enter address {address}")
    public PrePage enterAddressText(String address) {
        adressTextArea.sendKeys(address);
        return this;
    }

    @Step("Click on the desired address")
    public PrePage clickOnTheDesiredAdress() {
        desiredAdress.click();
        return this;
    }

    @Step("Click next button")
    public PrePage clickNextButton() {
        nextButton.click();
        return this;
    }

    @Step("Skip pre page")
    public PrePage skipPrePage(String address) {
        skipButton.click();
        geoLocationButton.click();
        valueEditText.click();
        adressTextArea.sendKeys(address);
        desiredAdress.click();
        nextButton.click();
        return this;
    }


}

