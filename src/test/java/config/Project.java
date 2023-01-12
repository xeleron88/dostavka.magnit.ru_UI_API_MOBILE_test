package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static config.App.validateProperty;

public class Project {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(Project.class);

    static {
        if ("API".equals(System.getProperty("tag"))) {
            validateProperty(config.apiBaseUrl(), "apiBaseUrl");
            validateProperty(App.config.httpSessId(), "httpSessId");
        } else {
            validateEnvDependentProperties();
        }
        logger.info(config.toString());
    }

    private static void validateEnvDependentProperties() {
        validateProperty(config.runIn(), "runIn");
        switch (config.runIn()) {
            case "browser_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "browser_local":
                validateProperty(config.browser(), "browser");
                break;
            case "android_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "android_browserstack":
                validateProperty(config.user(), "user");
                validateProperty(config.key(), "key");
                break;
            case "android_emulator":
                validateProperty(config.remoteDriver(), "remoteDriver");
                validateProperty(config.deviceName(), "deviceName");
                validateProperty(config.platformVersion(), "platformVersion");
                break;
            default:
                throw new IllegalStateException("Unexpected 'runIn' value: " + config);
        }
    }

    public static boolean isRemoteDriver() {
        return !(config.remoteDriver() == null) && !config.remoteDriver().isEmpty();
    }
}