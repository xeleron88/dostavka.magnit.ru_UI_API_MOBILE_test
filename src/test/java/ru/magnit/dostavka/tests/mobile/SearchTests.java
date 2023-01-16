package ru.magnit.dostavka.tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.magnit.dostavka.tests.TestBase;
import ru.magnit.dostavka.tests.mobile.pages.MainPage;
import ru.magnit.dostavka.tests.mobile.pages.PrePage;
import ru.magnit.dostavka.tests.mobile.pages.SearchPage;

@Tag("Android")
@Epic("Android")
@Feature("Search")
@Owner("xeleron88")
public class SearchTests extends TestBase {
    PrePage prePage = new PrePage();
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @Test
    @DisplayName("Ability to search for products")
    void abilityToSearhProducts() {
        prePage.skipPrePage("Уланская улица, 11");
        mainPage.clickToSearchView();
        searchPage.enterProductText("Молоко");
        searchPage.checkSearchResult("Молоко");
    }
}
