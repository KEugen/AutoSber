package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Settings {

    public WebDriver driver;
    public String setTypeStand(){
        String stand = "qa"; //выбор стенда qa/demo
        return stand;
    }

    public final String testsSelection(){
        final String tests = "site"; // site, admin or all
        return tests;
    }
}
