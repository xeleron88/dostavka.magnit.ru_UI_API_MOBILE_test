package ru.magnit.dostavka.tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.magnit.dostavka.tests.TestBase;
import ru.magnit.dostavka.tests.mobile.pages.PrePage;
import ru.magnit.dostavka.tests.mobile.pages.ProfilePage;

@Tag("Android")
@Epic("Android")
@Feature("Login")
@Owner("xeleron88")
public class LoginTests extends TestBase {
    PrePage prePage = new PrePage();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Ability to open profile page")
    void checkDeliveryAddress() {
        prePage.skipPrePage("Ростов-на-Дону, Уланская улица, 11");
        profilePage.clickToProfile();
        profilePage.checkProfileTitle();
    }

//    @Test
//    @DisplayName("Checking if a valid phone number has been entered")
//    void checkValidPhoneNumber() {
//        prePage.skipPrePage("Ростов-на-Дону, Уланская улица, 11");
//        profilePage.clickToProfile();
//        profilePage.enterPhoneNumber("9289566554");
//        profilePage.checkValidNumber();
//    }
//
//    @Test
//    @DisplayName("Checking if a invalid phone number has been entered")
//    void checkInvalidPhoneNumber() {
//        prePage.skipPrePage("Ростов-на-Дону, Уланская улица, 11");
//        profilePage.clickToProfile();
//        profilePage.enterPhoneNumber("1111111111");
//        profilePage.checkInvalidNumber();
//    }
}
