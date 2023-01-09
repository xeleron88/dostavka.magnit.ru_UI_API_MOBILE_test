package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {
    String login();
    String password();
    String phpSessId();
}