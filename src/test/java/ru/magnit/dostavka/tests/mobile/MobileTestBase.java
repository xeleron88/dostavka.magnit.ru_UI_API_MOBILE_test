package ru.magnit.dostavka.tests.mobile;

import ru.magnit.dostavka.tests.TestBase;
import ru.magnit.dostavka.tests.mobile.pages.MainPage;
import ru.magnit.dostavka.tests.mobile.pages.PrePage;
import ru.magnit.dostavka.tests.mobile.pages.ProfilePage;
import ru.magnit.dostavka.tests.mobile.pages.SearchPage;

public class MobileTestBase extends TestBase {
    PrePage prePage = new PrePage();
    ProfilePage profilePage = new ProfilePage();
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
}
