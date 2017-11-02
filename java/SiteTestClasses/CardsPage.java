package SiteTestClasses;

import Helper.AdditionalMethods;
import Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardsPage extends SetDriver {
    AdditionalMethods methods = new AdditionalMethods();
    Actions actions = new Actions(driver);
    //card list
    private By cardList = By.cssSelector(".g-tab_sber > div:nth-child(3) > div > div");
    // первый фонд
    private By firstFaundOnList = By.cssSelector(".g-tab_sber > div:nth-child(3) > div > div > a:nth-child(1)");
    // i-й фонд
    private By hoveredFaundOnList;
    //чекбокс-средечко (только для первой карточки!!!)
    private By hearthCheckbox = By.cssSelector(".g-tab_sber > div:nth-child(3) > div > div > a:nth-child(1) > div > div:nth-child(3) > div:nth-child(2)");
    // кнопка "Добавить фонд"/"Убрать фонд" (или направление)
    private By addCardButton = By.cssSelector(".b-card-page__card-button > div > span");

    // первое направление на странице фонда
    private By firstDirection = By.cssSelector(".b-card-list__cards > a:nth-child(1)");
    // выбранный фонд (selected)
    private By selectedFaund = By.cssSelector(".b-card.b-card_selected");

    private By addDirectionButton = By.cssSelector(".b-card-page__card-button");
    //все фонды данного направления (n сестринских элементов)
    private By cardContent = By.cssSelector(".b-card__content");
    // плашка при добаленных фондах
    private By cardsCountOnHeader = By.cssSelector(".b-funds-cart__text");
    private By cardsCountOnLogo = By.cssSelector(".b-header__funds-counter");
    private By goOnPopup = By.cssSelector(".b-action-popup-block_sber > div:nth-child(3)");

    private String firstFaundsId;
    private String selectedFaundId;
    private String firstDirectionId;

    private String lastCardId;

    public String getLastCardId() {
        return lastCardId;
    }

    public void setLastCardId(String id) {
        lastCardId = id;
    }

    public boolean idIsMatched(String firstId, String secondId) {
        if (firstId.endsWith(secondId)) return true;
        return false;
    }

    private String getLocatorForCard(int cardNumber) {
        return ".g-tab_sber > div:nth-child(3) > div > div > a:nth-child" + "(" + cardNumber + ")";
    }

    public void hoverOnCard(int cardNum) {
        hoveredFaundOnList = By.cssSelector(getLocatorForCard(cardNum));
        actions.moveToElement(driver.findElement(hoveredFaundOnList)).perform();
    }

    public void clickOnCard(int cardNum) {
        WebElement cardElement = driver.findElement(By.cssSelector(getLocatorForCard(cardNum)));
        setLastCardId(cardElement.getAttribute("href"));
        //lastCardId = cardElement.getAttribute("href");
        cardElement.click();
    }

    // проверяем ховер для первых двух карточек
    public boolean checkHoverOnFaund() {
        By lastHoveredCard;
        boolean firstHoverCheck;
        boolean secondHoverCheck;
        String cardClasses;

        hoverOnCard(1);
        cardClasses = driver.findElement(hoveredFaundOnList).getAttribute("class");
        firstHoverCheck = cardClasses.contains("b-card_hover");
        lastHoveredCard = hoveredFaundOnList;
        methods.Wait(500);
        hoverOnCard(2);
        cardClasses = driver.findElement(hoveredFaundOnList).getAttribute("class");
        secondHoverCheck = cardClasses.contains("b-card_hover") && !(driver.findElement(lastHoveredCard).getAttribute("class").endsWith("b-card_hover"));
        return firstHoverCheck && secondHoverCheck;
    }

    public void clickOnFirstCardsHearth() {
        driver.findElement(hearthCheckbox).click();
    }

    public void clickOnAddButton() {
        driver.findElement(addCardButton).click();
    }

    // пока не делаю, сложности с нахождением локатора для элемента чекбокса на странице
    public boolean checkHearthChkboxState() {
        return true;
    }

    public boolean cardIsSelected (final int cardNum) {
        String state = driver.findElement(By.cssSelector(getLocatorForCard(cardNum))).getAttribute("class");
        return state.endsWith("b-card_selected");
    }

    public boolean checkAddButtonState(final String expectedResult) {
            String actualResult = driver.findElement(addCardButton).getText();
            boolean dd = expectedResult.contentEquals(actualResult);
            return expectedResult.contentEquals(actualResult);
    }

    public boolean checkLogoCount(final int expectedResult) {
            int actualCount = Integer.valueOf(driver.findElement(cardsCountOnLogo).getText());
            return expectedResult == actualCount;
    }

    public boolean isLogoCountDisplayed() {
        return driver.findElement(cardsCountOnLogo).isDisplayed();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean clickOnFirstFaund () {
        //запоминаем id первого фонда в списке и кликаем по этому фонду
        methods.Wait(1000);
        firstFaundsId = driver.findElement(By.cssSelector(getLocatorForCard(1))).getAttribute("href");
        clickOnCard(1);
//        firstFaundsId = driver.findElement(firstFaundOnList).getAttribute("href");
//        driver.findElement(firstFaundOnList).click();
        return driver.getCurrentUrl().contains(firstFaundsId);
    }

    public boolean clickOnFirstDirection() {
        boolean correctCardsCountOnLogo;
        String currentCount;
        methods.Wait(1500);
        // добавляем фонд в список фондов
        driver.findElement(addCardButton).click();
        // проверка что кнопка позеленела и поменялась ???...

        methods.Wait(1000);
        // проверяем что счетчик в лого = 1
        currentCount = driver.findElement(cardsCountOnLogo).getText();

        // запоминаем id первого направления и переходим на страницу этого направления, (в атрибуте лежит весь URL)
        firstDirectionId = driver.findElement(firstDirection).getAttribute("href");
        driver.findElement(firstDirection).click();
        methods.Wait(1000);
        return (driver.getCurrentUrl().equals(firstDirectionId)) &&  "1".equals(currentCount)/*currentCount.equals("1") /*"1".equals(currentCount) correctCardsCountOnLogo*/;
    }

    public boolean findSelectedFaund() {
        boolean correctIdFlag;

        methods.Wait(2000);
        correctIdFlag = firstFaundsId.contains(driver.findElement(selectedFaund).getAttribute("href"));
        return (driver.findElement(selectedFaund).isDisplayed() && correctIdFlag);
    }

    public boolean addDirection() {
        int currentCountInLogo;
        int currentCountOnBanner;
        boolean correctButtonText;

        methods.Wait(1000);
        List<WebElement> cards = driver.findElements(cardContent);
        driver.findElement(addDirectionButton).click();
        methods.Wait(700);
        driver.findElement(goOnPopup).click();
        methods.Wait(700);

        //проверяем, что       значение_в_счетчике_баннера = значение_в_счетчике_лого - 1       НАВЕРНОЕ ЭТО НЕ НУЖНО ПРОВЕРЯТЬ ПОТОМУ ЧТО ВДРУГ В НАПРАВЛЕНИИ 1 ФОНД
        currentCountInLogo = Integer.valueOf(driver.findElement(cardsCountOnLogo).getText()).intValue();
        currentCountOnBanner = Integer.valueOf(driver.findElement(cardsCountOnHeader).getText().replaceAll("[^0-9]", "")).intValue();
        final int delta = currentCountInLogo - currentCountOnBanner;
        // и то, что кнопка поменялась на Убрать направление
        correctButtonText = driver.findElement(addCardButton).getText().equals("Убрать направление");
        System.out.println(delta == 1);
        System.out.println(correctButtonText);
        System.out.println(cards.size());
        // и то, что количество карточек равно значению_счетчка_в_лого
        return (delta == 1) &&   cards.size() == currentCountInLogo   &&  correctButtonText;
    }

    public boolean deleteDirection() {
        return true;
    }

    public boolean clickOnLogo() {
        return true;
    }






}
