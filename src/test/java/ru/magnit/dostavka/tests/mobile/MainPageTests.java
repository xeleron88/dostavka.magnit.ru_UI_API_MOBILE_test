package ru.magnit.dostavka.tests.mobile;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.magnit.dostavka.tests.TestBase;
import ru.magnit.dostavka.tests.mobile.domain.ShopTypes;
import ru.magnit.dostavka.tests.mobile.pages.MainPage;
import ru.magnit.dostavka.tests.mobile.pages.PrePage;


//@Tag("Android")
//@Epic("Android")
@Feature("MainPage")
@Owner("xeleron88")
public class MainPageTests extends TestBase {
    PrePage prePage = new PrePage();
    MainPage mainPage = new MainPage();
    @Test
    @DisplayName("Check delivery address")
    void checkDeliveryAddress() {
        prePage.skipPrePage("Ростов-на-Дону, Уланская улица, 11");
        mainPage.checkAddress("Уланская улица, 11");
    }

    @Test
    @DisplayName("Edit delivery address")
    void changeDeliveryAddress() {
        prePage.skipPrePage("Ростов-на-Дону, Уланская улица, 11");
        mainPage.clickArrowButton();
        mainPage.clickToEditImage();
        mainPage.clickToChangeButton();
        prePage.valueEditTextClick();
        prePage.enterAddressText("Ростов-на-Дону, Уланская улица, 15");
        prePage.clickOnTheDesiredAdress();
        prePage.clickNextButton();
        mainPage.checkAddress("Уланская улица, 15");
    }

    @ParameterizedTest(name = "{arguments}")
    @EnumSource(ShopTypes.class)
    @DisplayName("Ability to switch shop type")
    void switchShopType(ShopTypes shopTypes) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("Ability to switch shop type to: " + shopTypes.shopType));

        prePage.skipPrePage("Ростов-на-Дону, Социалистическая улица, 128");
        mainPage.changeShopType(shopTypes.shopType);
        mainPage.checkShopType(shopTypes.shopType);
    }

}
