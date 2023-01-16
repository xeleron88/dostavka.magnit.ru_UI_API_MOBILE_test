package ru.magnit.dostavka.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    private final SelenideElement queryEditText = $(AppiumBy.id("ru.magnit.express.android:id/queryEditText"));
    private final SelenideElement titleTextView = $(AppiumBy.id("ru.magnit.express.android:id/titleTextView"));

    @Step("Enter: {product}, in the search bar")
    public SearchPage enterProductText(String product) {
        queryEditText.sendKeys(product);
        return this;
    }

    @Step("Checking if search results contain {product}")
    public SearchPage checkSearchResult(String product) {
        queryEditText.shouldHave(text(product));
        return this;
    }



}
