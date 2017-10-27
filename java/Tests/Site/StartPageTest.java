/**
 getUrl: переходим на стартовую страницу
 нажимаем на кнопку "О проекте"
 запоминаем первую вкладку
 в попапе нажимаем на кнопку "Пользовательское соглашение", переводим фокус на 2 вкладку
 сравниваем url
 закрываем 2 вкладку и переводим фокус на 1ую
 в попапе нажимаем на кнопку "Публичная оферта", переводим фокус на 2 вкладку
 сраниваем url
 закрываем 2 вкладку и переводим фокус на 1ую
 нажимаем на кнопку "Закрыть" в попапе
 нажимаем на логотип Сбербанка,переводим фокус на 2 вкладку
 сравниваем url, закрываем 2 вкладку и переводим фокус на 1ую
 нажимаем на логотип mastercard, переводим фокус на 2ую вкладку
 сравниваем url
 */
package Tests.Site;

import AdminTestClasses.AdminTopic;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.StartPage;
import org.junit.Test;
import org.testng.Assert;
import java.util.Set;

public class StartPageTest extends SetDriver {

    @Test
    public void startPage() {
        GetUrl getUrl = new GetUrl();
        StartPage startPage = new StartPage();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#start");
        // нажимаем на кнопку "О проекте"
        startPage.clickOnAboutProjectButton();
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        // в попапе нажимаем на кнопку "Пользовательское соглашение"
        startPage.clickOnAgreementLink();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://sberbankvmeste.ru/files/personal.pdf");
        driver.close();
        driver.switchTo().window(parentWindowId);
        // в попапе нажимаем на кнопку "Публичная оферта"
        startPage.clickOnPublicOffer();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://sberbankvmeste.ru/files/offer.pdf");
        driver.close();
        driver.switchTo().window(parentWindowId);
        //нажимаем на кнопку "Закрыть в попапе"
        startPage.clickOnClosePopupButton();
        methods.Wait(1000);
        //проверяем закрытие попапа
        Assert.assertFalse(startPage.checkPopupClose(),"Попап О проекте не закрылся");
        //нажимаем на кнопку лого сбербанк
        startPage.clickOnSberbankLogoButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.sberbank.ru/ru/person");
        driver.close();
        driver.switchTo().window(parentWindowId);
        //нажимаем на лого mastercard
        startPage.clickOnMastercardLogoButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.mastercard.ru/ru-ru.html");
    }
}