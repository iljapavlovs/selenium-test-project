package com.mycompany.selenium.constants;


import com.mycompany.selenium.utils.ConfigReader;

public class Constants {

    public static final int WAIT_EXPLICIT_SECONDS = Integer.parseInt(ConfigReader.loadProperty("wait.explicit.seconds"));
    public static final String APP_URL = ConfigReader.loadProperty("app.url");


}
