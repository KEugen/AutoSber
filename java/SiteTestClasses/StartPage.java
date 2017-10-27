package SiteTestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static Helper.SetDriver.driver;

public class StartPage {
    By aboutProject = By.xpath("//*[@class='b-start-page__about-btn']");
    By agreementLink = By.xpath("//*[@class='b-about-block__privacy']/a[1]");
    By publicOffer = By.xpath("//*[@class='b-about-block__privacy']/a[2]");
    By closePopupButton = By.xpath("//*[@class='b-about-block__close-button']");
    By sberbankLogo = By.xpath("//*[@class='i-control g-icon g-icon_sber g-icon_img_logo-sber_white']");
    By mastercardLogo = By.xpath("//*[@class='i-control g-icon g-icon_sber g-icon_img_mastercard-white-min']");
    By popupAboutProject = By.xpath("//*[@class='i-control g-modal g-modal_sber g-modal_about-modal g-modal_hover']");

    public void clickOnAboutProjectButton() {
        driver.findElement(aboutProject).click();
    }

    public void clickOnAgreementLink() {
        driver.findElement(agreementLink).click();
    }

    public void clickOnPublicOffer() {
        driver.findElement(publicOffer).click();
    }

    public void clickOnClosePopupButton() {
        driver.findElement(closePopupButton).click();
    }

    public void clickOnSberbankLogoButton() {
        driver.findElement(sberbankLogo).click();
    }

    public void clickOnMastercardLogoButton() {
        driver.findElement(mastercardLogo).click();
    }

    public boolean checkPopupClose() {
        try {
            return driver.findElement(popupAboutProject).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}