/**
 - открываем admin.sber-vmeste.qa.lan или demo.sberbankvmeste.ru
 - переходим на таб Фонды
 - addNewFundAndCheckValidation:
 нажимаем на кнопку добавления нового тнф("плюсик")
 на странице создания тнф нажимаем на кнопку "Создать", проверяем ошибки валидации в поле "Заголовок","Описание",блок с добавлением Темы
 добавляем заголовок TitleFund, описание DescriptionFund,
 нажимаем на кнопку загрузки картинки, загружаем картинку с форматом jpg
 раскрываем первую тему в списке, выбираем первое направление в теме (тема выбирается автоматич)
 не выбираем чекбокс "Опубликован", нажимаем на кнопку создания темы "Создать"
 проверяем ошибку валидации о неверном формате картинки
 нажимаем на кнопку загрузки картинки, загружаем картинку с форматом png
 нажимаем на кнопку создания темы "Создать"
 - searchTdf: вводим в поиск название новой темы и проверяем. что переключатель выключен(не опубликовано)
 - записываем в строку id фонда
 - переходим на страницу фонда по id (#card?id="+idFund+)
 - проверяем, что отображается 404 страница
 - переходим обратно в админку в таб "Фонды"
 - searchTopic: находим новый фонд в списке фондов
 - editAndPublishTdf:
 на странице со списком фондов нажимаем на кнопку редактирования фонда
 изменяем название темы (New)
 изменяем описание фонда
 нажимаем на чебокс "Опубликован"
 - saveChanges: нажимаем на кнопку "Сохранить" на странице редакт фонда
 - searchTdf: находим отредактированный фонд в списке фондов
 - проверяем, что в списке фондов отображается отредактированный заголовок фонда
 - проверяем, что в списке фондов отображается включенный переключатель(Опубликована)
 - editAndPublishTdf: изменяем заголовок и описание
 - clickOnBackButton: не сохраняем отредактированную тему, а нажимаем на кнопку "Назад"
 - searchTdf
 - проверяем, что отображается старый заголовок и по кнопке "Назад" изменения не применились
 - записываем в строку заголовок направления, в которое входит фонд
 - нажимаем на таб Направления
 - ищем направление, к которому принадлежит фонд
 - записываем в строку название темы, в котором состоит направление с фондом
 - checkPublishedTdf():
 проверяем статус переключетяля у темы, если "Нет", то нажимаем на редактирование темы,кликаем на кнопку Опубликовать,сохраняем изменения
 - переходит на сайт по id фонда
 - проверяем новый заголовок и описание до кнопки Подробнее
 - нажимаем на кнопку Подробнее и сравниваем все описание
 - сравниваем заголовок направления, в котором состоит фонд, на сайте с заголвком направления в админке
 - запоминаем текущую вкладку, нажимаем на первую ссылку в описании
 - переводим фокус на вторую вкладку и сравниваем url,закрываем вторую вкладку и переводим фокус на 1ую
 - нажимаем на вторую ссылку в описании
 - закрываем вкладку, переводим фокус на 1 вкладку
 - переходим в админку на страницу фондов
 - находим новый фонд
 - нажимаем на кнопку редактирования фонда
 - на странице редактирования фонда сравниваем первый заголовок темы, в которое входит направление с темой на странице направления
 - нажимаем на кнопку назад
 - находим новый фонд
 - deleteTopic():
 нажимаем на кнопку Удаления фонда на странице со списком фондов
 в попапе с подтверждением удаления нажимаем на Нет
 нажимаем на кнопку Удалить
 в попапе с подтверждением удаления нажимаем на Да
 - в поиске ищем направление
 - проверяем,что направеление не найдено и  отображается текст No matching records found
 - переходим на сайт по id направления
 - проверяем, что отображается 404 страница
 */

package Tests.Admin;

import AdminTestClasses.AdminDirection;
import AdminTestClasses.AdminFund;
import AdminTestClasses.AdminTopic;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AdminFundTest extends SetDriver{

    @Test
    public void checkFund() {
        GetUrl getUrl = new GetUrl();
        AdminTopic adminTopic = new AdminTopic();
        AdminDirection adminDirection = new AdminDirection();
        AdminFund adminFund = new AdminFund();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getAdminUrl();
        methods.Wait(1000);
        adminFund.clickOnTabFunds();
        adminFund.addNewFundAndCheckValidation(
                "TitleFund",
                "DescriptionFund",
                "Поле обязательно для заполнения",
                "Поле обязательно для заполнения",
                "Фонд должен входить как минимум в одно направление.",
                "Формат файла должен быть в PNG");
        adminTopic.searchTdf("TitleFund");
        Assert.assertEquals(adminTopic.getSwitchStatus(),"Нет No");
        String idFund = adminDirection.getIdTdf();
        getUrl.getSiteUrlWithStr("#card?id="+idFund+"");
        Assert.assertEquals(adminDirection.getErrorNotFoundPage(),"404");
        getUrl.getAdminUrlWithStr("admin/entity/fund");
        adminTopic.searchTdf("TitleFund");
        adminDirection.editAndPublishTdf("New"," Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
                " Aenean commodo ligula eget dolor. Aenean ma\n" +
                "1. Cайт фонда Хабенского: [url]http://bfkh.ru/[/url] \n" +
                "2. [url=Cайт фонда Хабенского]http://bfkh.ru/[/url]");
        methods.Wait(1000);
        adminTopic.saveChanges();
        //проверяем, что изменения применились в админке
        adminTopic.searchTdf("TitleFundNew");
        Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TitleFundNew");
        Assert.assertEquals(adminTopic.getSwitchStatus(),"Да Yes");
        //проверяем,что изменения не сохраняются при нажатии на кнопку "Назад"
        adminDirection.editAndPublishTdf("1","1");
        adminTopic.clickOnBackButton();
        adminTopic.searchTdf("TitleFundNew");
        Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TitleFundNew");
        //запоминаем заголовок направления, в которое входит фонд
        String titleDirectionWithFundInAdmin = adminFund.getDirectionWithFundInAdmin();
        adminDirection.clickOnTabDirection();
        adminTopic.searchTdf(titleDirectionWithFundInAdmin);
        //запоминаем название темы, в которой состоит направление с фондом в админке
        String titleTopicWithDirection = adminFund.getTitleTopicWithDirection();
        adminDirection.checkPublishedTdf();
        getUrl.getSiteUrlWithStr("#card?id="+idFund+"");
        methods.Wait(1000);
        Assert.assertEquals(adminDirection.getTitleTdfOnSite(),"TitleFundNew");
        methods.Wait(1000);
        Assert.assertEquals(adminDirection.getDescriptionTdfOnSite(),"DescriptionFund Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
                " Aenean commodo ligula eget dolor.\n" +
                "\n" +
                "подробнее");
        adminDirection.clickOnMoreDescriptionButton();
        Assert.assertEquals(adminDirection.getDescriptionTdfOnSite(),"DescriptionFund Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
                " Aenean commodo ligula eget dolor. Aenean ma\n" +
                "1. Cайт фонда Хабенского: http://bfkh.ru/\n" +
                "2. Cайт фонда Хабенского");
        //сравниваем заголовок направления на сайте с заголовком направления в админке
        Assert.assertEquals(adminFund.getTitleDirectionOnSite(),titleDirectionWithFundInAdmin);
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        methods.Wait(1000);
        //проверяем переходы по ссылкам в описании
        adminDirection.clickOnFirstLinkInDescription();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(),"http://bfkh.ru/");
        driver.close();
        driver.switchTo().window(parentWindowId);
        adminDirection.clickOnSecondLinkInDescription();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(1000);
        Assert.assertEquals(driver.getCurrentUrl(),"http://bfkh.ru/");
        driver.close();
        driver.switchTo().window(parentWindowId);
        getUrl.getAdminUrlWithStr("admin/entity/fund");
        adminTopic.searchTdf("TitleFundNew");
        //проверяем, что темы на странице редакт фонда соответсвует теме,в которую входит направление
        adminTopic.clickOnEditTdfButton();
        Assert.assertEquals(adminFund.getTitleTopicOnSite(),titleTopicWithDirection);
        adminTopic.clickOnBackButton();
        //проверяем удаление фонда по кнопке на странице списка фондов
        adminTopic.searchTdf("TitleFundNew");
        adminTopic.deleteTopic();
        adminTopic.searchTdf("TitleFundNew");
        Assert.assertEquals(adminTopic.getNotFoundTopicInSearch(),"No matching records found");
        //проверка удаления темы на сайте
        getUrl.getSiteUrlWithStr("#card?id="+idFund+"");
        Assert.assertEquals(adminDirection.getErrorNotFoundPage(),"404");
    }
}
