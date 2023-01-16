package ru.magnit.dostavka.drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;

public class LocalAndroidDriver implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Configuration.browserSize = null;
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setCapability("uiautomator2ServerInstallTimeout", 90000);
        options.setDeviceName(config.deviceName());
        options.setPlatformVersion(config.platformVersion());
        options.setApp(getApk().getAbsolutePath());
        options.setLocale("ru");
        options.setLanguage("ru");
        options.setAppPackage("ru.magnit.express.android");
        options.setAppActivity("ru.magnit.client.AppActivity");
        try {
            return new AndroidDriver(new URL(config.remoteDriver()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File getApk() {
        return new File("src/test/resources/apk/ru.magnit.express.android_3.7.2_1147.apk");
    }
}
