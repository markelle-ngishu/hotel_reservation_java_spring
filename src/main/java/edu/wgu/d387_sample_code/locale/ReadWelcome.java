package edu.wgu.d387_sample_code.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class ReadWelcome {
    private Locale locale;
    private ResourceBundle resourceBundle;

    public String getWelcomeMessage() {
        return resourceBundle.getString("Welcome");
    }

    public ReadWelcome() {

    }

    public ReadWelcome(String language, String country){
        locale = new Locale(language,country);
        resourceBundle = ResourceBundle.getBundle("Resources",locale);
        System.out.printf("%s, %s%n",language, country);
    }

}
