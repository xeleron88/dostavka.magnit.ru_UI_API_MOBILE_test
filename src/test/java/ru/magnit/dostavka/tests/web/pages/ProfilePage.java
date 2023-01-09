package ru.dostavka.magnit.web.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement profileTitle = $(by("data-test-id", "profile-title"));

    @Step("Click on bookmate header logo")
    public ProfilePage checkProfileTitle(String expectedTitle) {
        profileTitle.shouldHave(text(expectedTitle));
        return this;
    }
}
