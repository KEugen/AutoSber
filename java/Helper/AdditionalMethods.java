package Helper;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

public class AdditionalMethods extends SetDriver{

    // метод, который ждет наступления какого-то события, прежде чем продолжит выполнение команд
    public void Wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    // получение рандомного email c доменом
    public String generateRandomEmail(){
        String str1 = "test";
        String str2 = "@p33.org";

        int a = 0;
        int b = 1000;
        int FirstRandomNumber = a + (int) (Math.random() * b);
        int SecondRandomNumber = a + (int) (Math.random() * b);

        String str3 =  FirstRandomNumber + str1 + SecondRandomNumber + str2;
        return str3;
    }

    // удаление домена в рандомном email
    public String deleteDomainInEmail(String email){
        return email.replaceAll("@p33.org","");
    }

    // загрузка картинки по абсолютному пути в JPG/PNG
    public void imageDownload(String imageFormat) {
        if (imageFormat.equals("jpg") || imageFormat.equals("JPG")) {
            StringSelection stringSelection = new StringSelection("C:\\autotest-sbervmeste\\1.jpg");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
        else if (imageFormat.equals("png") || imageFormat.equals("PNG")){
            StringSelection stringSelection = new StringSelection("C:\\autotest-sbervmeste\\1.png");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
        else { Assert.fail("Указан неверный формат картинки");
        }

        Wait(1500);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // изменение фокуса с одной страницы на вторую
    public void moveFocusToTheNewWindow(final Set<String> oldWindowsSet){
        String newWindows = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindowsSet = webDriver.getWindowHandles();
                newWindowsSet.removeAll(oldWindowsSet);
                return newWindowsSet.size() > 0 ?
                        newWindowsSet.iterator().next() : null;
            }
        });
        driver.switchTo().window(newWindows);
    }

    // скролл старинцы на заданное количество пикселей
    public void scroll(String pixels){
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,"+pixels+")");
    }

    // обработка консольных ошибок
    public void getBrowserLogs(){
        for (LogEntry logEntry : driver.manage().logs().get("browser").filter(Level.SEVERE)) {
            System.out.println(logEntry);
            Assert.fail();
        }
    }
}


