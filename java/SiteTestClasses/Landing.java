package SiteTestClasses;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import Helper.Settings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Landing extends SetDriver {

    private AdditionalMethods methods = new AdditionalMethods();

    private By landingSberbankButton = By.cssSelector(".b-landing-main__header-right > a:nth-child(1) > div");
    private By landingMastercardButton = By.cssSelector(".b-landing-main__header-right > a:nth-child(2) > div");

    private By landingStartButton = By.cssSelector(".b-landing-main__button-start");
    private By landingAlreadyButton = By.cssSelector(".b-landing-main__button-already");

    private By landingMediaButton = By.cssSelector(".b-landing-main__information > a:nth-child(1)");
    private By landingForNKOButton = By.cssSelector(".b-landing-main__information > a:nth-child(2)");

    private By landingFondsButton = By.cssSelector(".b-landing-project__funds-wrapper > a > div");
    private By landingDirectionsButton = By.cssSelector(".b-landing-project__directions-wrapper > a > div");

    private By landingFooterStartButton = By.cssSelector(".b-landing-let-together__button-wrapper > a > div");
    private By landingFooterSberbankButton = By.cssSelector(".b-landing-page__logos > a:nth-child(1) > div.b-landing-page__logo > div");
    private By landingFooterMasterCardButton = By.cssSelector(".b-landing-page__logos > a:nth-child(2) > div.b-landing-page__logo > div");
    private By landingFooterMediaButton = By.cssSelector(".b-landing-page__information > a:nth-child(2)");
    private By landingFooterForNKOButton = By.cssSelector(".b-landing-page__information > a:nth-child(3)");

    private By landingVkSharing = By.cssSelector(".g-share__share_vk");
    private By landingOkSharing = By.cssSelector(".g-share__share_ok");
    private By landingFbSharing = By.cssSelector(".g-share__share_fb");
    private By landingTwitterSharing = By.cssSelector(".g-share__share_tw");

    private By landingSlidersCircle1 = By.cssSelector(".b-landing-how-works__steps-wrapper > div:nth-child(1)");
    private By landingSlidersCircle2 = By.cssSelector(".b-landing-how-works__steps-wrapper > div:nth-child(2)");
    private By landingSlidersCircle3 = By.cssSelector(".b-landing-how-works__steps-wrapper > div:nth-child(3)");

    private By landingSlidersText1 = By.cssSelector(".b-landing-how-works__content > div:nth-child(1) > div");
    private By landingSlidersText2 = By.cssSelector(".b-landing-how-works__content > div:nth-child(2) > div");
    private By landingSlidersText3 = By.cssSelector(".b-landing-how-works__content > div:nth-child(3) > div");

    private By landingHeart1 = By.cssSelector(".b-landing-let-together__left-grid > div:nth-child(2) > svg > a");
    private By landingHeart2 = By.cssSelector(".b-landing-let-together__left-grid > div:nth-child(3) > svg > a");
    private By landingHeart3 = By.cssSelector(".b-landing-let-together__left-grid > div:nth-child(4) > svg > a");

    private By landingHeart4 = By.cssSelector(".b-landing-let-together__right-grid > div:nth-child(4) > svg > a");
    private By landingHeart5 = By.cssSelector(".b-landing-let-together__right-grid > div:nth-child(5) > svg > a");
    private By landingHeart6 = By.cssSelector(".b-landing-let-together__right-grid > div:nth-child(6) > svg > a");

    private ArrayList<By> ListOfHearths = new ArrayList<>();
    private ArrayList<By> Cirles = new ArrayList<>();
    private ArrayList<By> Texts = new ArrayList<>();

    public Landing() {

        Cirles.add(landingSlidersCircle1);
        Cirles.add(landingSlidersCircle2);
        Cirles.add(landingSlidersCircle3);

        Texts.add(landingSlidersText1);
        Texts.add(landingSlidersText2);
        Texts.add(landingSlidersText3);

        ListOfHearths.add(landingHeart1);
        ListOfHearths.add(landingHeart2);
        ListOfHearths.add(landingHeart3);
        ListOfHearths.add(landingHeart4);
        ListOfHearths.add(landingHeart5);
        ListOfHearths.add(landingHeart6);
    }


    public void clickInLandingButtonsInNewTab(ArrayList<String> expectedResult){
        //methods = new AdditionalMethods();

        ArrayList buttonsInNewTab = new ArrayList();
        buttonsInNewTab.add(landingSberbankButton);
        buttonsInNewTab.add(landingMastercardButton);
        buttonsInNewTab.add(landingFooterSberbankButton);
        buttonsInNewTab.add(landingFooterMasterCardButton);
        buttonsInNewTab.add(landingVkSharing);
        buttonsInNewTab.add(landingOkSharing);
        buttonsInNewTab.add(landingFbSharing);
        buttonsInNewTab.add(landingTwitterSharing);

        for(int i=0; i<buttonsInNewTab.size(); i++) {
            String parentWindowId = driver.getWindowHandle();
            final Set<String> oldWindowsSet = driver.getWindowHandles();
            methods.Wait(300);
            driver.findElement((By)buttonsInNewTab.get(i)).click();
            methods.Wait(300);
            methods.moveFocusToTheNewWindow(oldWindowsSet);
            Assert.assertEquals(driver.getTitle(), expectedResult.get(i));
            driver.close();
            driver.switchTo().window(parentWindowId);
        }
    }

    public void clickInLandingButtons(ArrayList<String> expectedResult){
        //methods = new AdditionalMethods();

        ArrayList buttons = new ArrayList();
        buttons.add(landingStartButton);
        buttons.add(landingAlreadyButton);
        buttons.add(landingMediaButton);
        buttons.add(landingForNKOButton);
        buttons.add(landingFondsButton);
        buttons.add(landingDirectionsButton);
        buttons.add(landingFooterStartButton);
        buttons.add(landingFooterMediaButton);
        buttons.add(landingFooterForNKOButton);

        for(int i=0; i<buttons.size(); i++) {
            methods.Wait(1000);
            driver.findElement((By)buttons.get(i)).click();
            methods.Wait(1000);
            Assert.assertEquals(driver.getTitle(), expectedResult.get(i));
            driver.navigate().back();
        }
    }

    public String clickOnSlider(int currentNumber) {

//        ArrayList<By> Cirles = new ArrayList<>();
//        Cirles.add(landingSlidersCircle1);
//        Cirles.add(landingSlidersCircle2);
//        Cirles.add(landingSlidersCircle3);
//
//        ArrayList<By> Texts = new ArrayList<>();
//        Texts.add(landingSlidersText1);
//        Texts.add(landingSlidersText2);
//        Texts.add(landingSlidersText3);

        methods.Wait(1000);
        driver.findElement(Cirles.get(currentNumber)).click();
        methods.Wait(750);

        return driver.findElement(Texts.get(currentNumber)).getText();
    }

    public Boolean waitAutoSwitching (int expectedSlide) {

//        ArrayList<By> Texts = new ArrayList<>();
//        Texts.add(landingSlidersText1);
//        Texts.add(landingSlidersText2);
//        Texts.add(landingSlidersText3);

        // ждем около 5 сек, пока слайд сам поменяется
        methods.Wait(5250);
        //methods.Wait(750);
        return driver.findElement(Texts.get(expectedSlide)).isDisplayed();
    }

    public Boolean clickOnHearts(int HeartNumber) {

        String heartsUrl;
        String actualHeartsUrl;

        methods.Wait(1000);
        heartsUrl = driver.findElement(ListOfHearths.get(HeartNumber)).getAttribute("xlink:href");
        System.out.println(heartsUrl);
        driver.findElement(ListOfHearths.get(HeartNumber)).click();
        methods.Wait(2000);
        actualHeartsUrl = driver.getCurrentUrl();
        System.out.println(actualHeartsUrl);
        System.out.println(actualHeartsUrl.contains(heartsUrl));
        driver.navigate().back();

        return (actualHeartsUrl.contains(heartsUrl));
    }

}
