package Tests.Site;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.Landing;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LandingTest extends SetDriver {

    @Test
    public void checkLanding(){
        GetUrl getUrl = new GetUrl();
        Landing landing = new Landing();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#landing");
        Assert.assertEquals(driver.getTitle(), "Сбербанк Вместе – простой способ помогать");

        ArrayList<String> expectedResultForOtherButtons = new ArrayList();
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");
        expectedResultForOtherButtons.add("Вход или Регистрация | Сбербанк Вместе");
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");
        expectedResultForOtherButtons.add("Фонды | Сбербанк Вместе");
        expectedResultForOtherButtons.add("Направления | Сбербанк Вместе");
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");
        expectedResultForOtherButtons.add("Сбербанк Вместе – простой способ помогать");

        //landing.clickInLandingButtons(expectedResultForOtherButtons);

        ArrayList<String> expectedResultForSberbankAndMastercard = new ArrayList();
        expectedResultForSberbankAndMastercard.add("«Сбербанк» - Частным клиентам");
        expectedResultForSberbankAndMastercard.add("Главная | Mastercard");
        expectedResultForSberbankAndMastercard.add("«Сбербанк» - Частным клиентам");
        expectedResultForSberbankAndMastercard.add("Главная | Mastercard");
        expectedResultForSberbankAndMastercard.add("ВКонтакте | Вход");
        expectedResultForSberbankAndMastercard.add("Одноклассники");
        expectedResultForSberbankAndMastercard.add("Facebook");
        expectedResultForSberbankAndMastercard.add("Поделиться ссылкой в Твиттере");


        //landing.clickInLandingButtonsInNewTab(expectedResultForSberbankAndMastercard);


        // проверяем работу слайдера
        ArrayList<String> expectedTextsOnSlide = new ArrayList<>();
        expectedTextsOnSlide.add("Выбирайте, кому помочь");
        expectedTextsOnSlide.add("Настройте регулярные пожертвования");
        expectedTextsOnSlide.add("Получайте ежемесячные отчеты");

        for (int i = expectedTextsOnSlide.size() - 1; i >= 0 ; i--) {

            String actualText = landing.clickOnSlider(i);
            Assert.assertEquals(expectedTextsOnSlide.get(i), actualText);

        }

        // автопереключение
        Assert.assertTrue("Не работает автопереключение слайдов",landing.waitAutoSwitching(1));
        Assert.assertTrue("Не работает автопереключение слайдов",landing.waitAutoSwitching(2));
        Assert.assertTrue("Не работает автопереключение слайдов",landing.waitAutoSwitching(0));

        // проверяем работу сердечек <3
        for (int i = 0; i <= 5; i++) {
            Assert.assertTrue(i + "-е сердечко не работает",landing.clickOnHearts(i));
        }
        //Assert.assertTrue(landing.clickOnHearts());
    }
}
