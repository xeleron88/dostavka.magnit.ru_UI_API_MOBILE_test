package ru.magnit.dostavka.drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import config.Project;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BrowserstackAndroidDriver implements WebDriverProvider {

    private static final String remoteDriver = "http://hub.browserstack.com/wd/hub";

    public static URL getBrowserstackUrl() {
        try {
            return new URL(remoteDriver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Configuration.browserSize = null;
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
        mutableCapabilities.setCapability("browserstack.user", config.user());
        mutableCapabilities.setCapability("browserstack.key", config.key());
        mutableCapabilities.setCapability("app", config.app());
        mutableCapabilities.setCapability("device", config.deviceName());
        mutableCapabilities.setCapability("os_version", config.platformVersion());
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("language", "ru");
        mutableCapabilities.setCapability("locale", "RU");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

}
