package Tests.Site;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.CardsPage;
import org.junit.Assert;
import org.junit.Test;

public class CardsPageTest extends SetDriver {

    @Test
    public void checkCardsPage() {
        GetUrl getUrl = new GetUrl();
        CardsPage cardsPage = new CardsPage();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#list?category=funds");

        Assert.assertTrue("Некорректно работает ховер карточек", cardsPage.checkHoverOnFaund());

        // по средечку на странице всех фондов не кликаем
//        cardsPage.clickOnFirstCardsHearth();
//        methods.Wait(500);
//        Assert.assertTrue("Карточка не выделилась при выставлении чекбокса-сердечка", cardsPage.cardIsSelected(1));

        // кликаем на первый фонд
        cardsPage.clickOnCard(1);
        methods.Wait(1500);

        // проверяем, что открылся корректный фонд, счетчик в лого корректный и состояние кнопки Добавить тоже корректное
        Assert.assertTrue("Открылся не тот фонд", cardsPage.idIsMatched(driver.getCurrentUrl(), cardsPage.getLastCardId()));
        Assert.assertTrue("Неверное состояние кнопки", cardsPage.checkAddButtonState("Добавить фонд"));
        Assert.assertTrue("Неверный счетчик в лого", cardsPage.checkLogoCount(1));
        // Меняем состояние кнопки (убираем фонд)
        cardsPage.clickOnAddButton();
        methods.Wait(1000);
        Assert.assertTrue("Неверное состояние кнопки", cardsPage.checkAddButtonState("Добавить фонд"));
        // логотип в лого скрыт
        Assert.assertTrue("счетчик в лого не скрыт", cardsPage.isLogoCountDisplayed());





        //cardsPage.clickOnCard(1);

        //Assert.assertTrue("Переход на страницу не того фонда", cardsPage.clickOnFirstFaund());
        //Assert.assertTrue("Переход на страницу не того направления или неверный счетчик в лого", cardsPage.clickOnFirstDirection());
        //Assert.assertTrue("Выбранный фонд не найден на странице направления", cardsPage.findSelectedFaund());
        //Assert.assertTrue("Кнопка не поменялась на Убрать направление или неверный счетчик", cardsPage.addDirection());
    }


}
