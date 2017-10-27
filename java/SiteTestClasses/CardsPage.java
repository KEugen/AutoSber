package SiteTestClasses;

import Helper.AdditionalMethods;
import Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CardsPage extends SetDriver {
    AdditionalMethods methods = new AdditionalMethods();

    // страница карточки
    private By addDirectionButton = By.cssSelector(".b-card-page__card-button");
    //все фонды данного направления (n сестринских элементов)
    private By cardContent = By.cssSelector(".b-card__content");
    // плашка при добаленных фондах
    private By cardsCountOnHeader = By.cssSelector(".b-funds-cart__text");
    private By cardsCountOnLogo = By.cssSelector(".b-header__left-column > span");
    private By goOnPopup = By.cssSelector(".b-action-popup-block_sber > div:nth-child(3)");


    public boolean clickOnAddButton() {
        List<WebElement> cards = driver.findElements(cardContent);
        //int cardsCount = cards.size();
        //System.out.println(cards.size());
        driver.findElement(addDirectionButton).click();
        methods.Wait(1000);
        driver.findElement(goOnPopup).click();
        String expectedlCounts = driver.findElement(cardsCountOnHeader).getText();
        String expectedCountsOnLogo = driver.findElement(cardsCountOnLogo).getText();
        String actualCounts = " " + cards.size() + " " + "фонд";
        String actualCountsOnLogo = String.valueOf(cards.size());
        return (expectedCountsOnLogo.contains(actualCountsOnLogo) && expectedlCounts.contains(actualCounts));
    }




}
