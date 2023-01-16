package ru.magnit.dostavka.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement arrowImageViewButton = $(AppiumBy.id("ru.magnit.express.android:id/arrowImageView"));
    private final SelenideElement editImageView = $(AppiumBy.id("ru.magnit.express.android:id/editImageView"));
    private final SelenideElement changeButton = $(AppiumBy.id("ru.magnit.express.android:id/changeButton"));
    private final SelenideElement adressText = $(AppiumBy.id("ru.magnit.express.android:id/addressTextView"));
    private final SelenideElement serviceTextView = $(AppiumBy.id("ru.magnit.express.android:id/serviceTextView"));
    private final SelenideElement searchView = $(AppiumBy.id("ru.magnit.express.android:id/searchView"));

    @Step("Click arrow button")
    public MainPage clickArrowButton() {
        arrowImageViewButton.click();
        return this;
    }

    @Step("Check address in the street text area")
    public MainPage checkAddress(String address) {
        adressText.shouldHave(text(address));
        return this;
    }

    @Step("Click to edit image")
    public MainPage clickToEditImage() {
        editImageView.click();
        return this;
    }

    @Step("Click to change button")
    public MainPage clickToChangeButton() {
        changeButton.click();
        return this;
    }

    @Step("Change shop type")
    public MainPage changeShopType(String shopType) {
        $(byAttribute("text", shopType)).click();
        return this;
    }

    @Step("Check the selected shop type")
    public MainPage checkShopType(String shopType) {
        serviceTextView.shouldHave(text(shopType));
        return this;
    }

    @Step("Click to search view")
    public MainPage clickToSearchView() {
        searchView.click();
        return this;
    }



}
