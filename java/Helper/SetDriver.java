package Helper;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SetDriver {
    public static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver" , "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDownAfterClass() throws IOException {
        //driver.quit();
    }
}
