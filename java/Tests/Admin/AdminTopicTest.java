/**
 - открываем admin.sber-vmeste.qa.lan или demo.sberbankvmeste.ru
 - addNewTopicAndCheckValidation:
 нажимаем на кнопку добавления новой темы("плюсик")
 на странице создания темы нажимаем на кнопку "Создать", проверяем ошибки валидации в поле "Заголвок" и "Описание"
 добавляем заголовок TestTitleTopic, описание TestDescriptionTopic,
 нажимаем на кнопку загрузки картинки, загружаем картинку
 не выбираем чекбокс "Опубликован", нажимаем на кнопку создания темы "Создать"
 - searchTdf: вводим в поиск название новой темы и проверяем. что переключатель выключен(не опубликована)
 - переходим на страницу направлений на сайте #list?category=directions
 - checkTopicOnSite: проверяем, что новой статьи нет на сайте,т.к. чекбокс "Опубликован" не был выбран
 - переходим обратно в админку
 - searchTopic: находим новую тему в списке тем
 - editAndPublishTopic:
 на странице со списком тем нажимаем на кнопку редактирования темы
 изменяем название темы (New)
 нажимаем на чебокс "Опубликован"
 - saveChanges: нажимаем на кнопку "Сохранить" на странице редакт темы
 - searchTopic: находим отредактированную тему в списке тем
 - проверяем, что в списке тем отображается отредактированный заголовок темы
 - проверяем, что в списке тем отображается включенный переключатель(Опубликована)
 - editAndPublishTopic
 - clickOnBackButton: не сохраняем отредактированную тему, а нажимаем на кнопку "Назад"
 - searchTopic
 - проверяем, что отображается старый заголовок и по кнопке "Назад" изменения не применились
 - переходим на страницу напрвлений на сайте #list?category=directions
 - проверяем, что тема отображается на сайте(из-за вкл чекбокса "Опубликован") с новым заголовком
 - переходим обратно в админку
 - searchTopic
 - deleteTopic:
 нажимаем на кнопку удаления темы на странице со списком тем
 в попапе с подтверждением удаления нажимаем на кнопку "Нет"
 повторно нажимаем на кнопку удаления темы
 в попапе с подтвержденем удаления темы нажимаем на кнопку "Да"
 - searchTopic: вводим в поиск название удаленной темы и проверяем, что тема не найдена
 - переходим на страницу напрвлений на сайте #list?category=directions
 - проверяем, что удаленная тема не отображается в списке тем на сайте
 */

package Tests.Admin;
import AdminTestClasses.AdminDirection;
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
import java.util.concurrent.TimeUnit;

public class AdminTopicTest extends SetDriver {

    @Test
    public void topic(){
        GetUrl getUrl = new GetUrl();
        AdminTopic adminTopic = new AdminTopic();

        getUrl.getAdminUrl();
        adminTopic.addNewTopicAndCheckValidation("TestTitleTopic","TestDescriptionTopic");
        adminTopic.searchTdf("TestTitleTopic");
        Assert.assertEquals(adminTopic.getSwitchStatus(),"Нет No");
        getUrl.getSiteUrlWithStr("#list?category=directions");
        adminTopic.checkTopicOnSite();
        getUrl.getAdminUrl();
        adminTopic.searchTdf("TestTitleTopic");
        adminTopic.editAndPublishTopic("New");
        adminTopic.saveChanges();
        //проверяем, что изменнения применились в админке
        adminTopic.searchTdf("TestTitleTopicNew");
        Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TestTitleTopicNew");
        Assert.assertEquals(adminTopic.getSwitchStatus(),"Да Yes");
        //проверяем,что изменения не сохраняются при нажатии на кнопку "Назад"
        adminTopic.editAndPublishTopic("1");
        adminTopic.clickOnBackButton();
        adminTopic.searchTdf("TestTitleTopicNew");
        Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TestTitleTopicNew");
        //проверяем,что изменения применились на сайте
        getUrl.getSiteUrlWithStr("#list?category=directions");
        Assert.assertEquals(adminTopic.getTitleNewTopicOnSite(),"TestTitleTopicNew");
        getUrl.getAdminUrl();
        adminTopic.searchTdf("TestTitleTopicNew");
        //проверяем удаление темы
        adminTopic.deleteTopic();
        adminTopic.searchTdf("TestTitleTopicNew");
        Assert.assertEquals(adminTopic.getNotFoundTopicInSearch(),"No matching records found");
        getUrl.getSiteUrlWithStr("#list?category=directions");
        adminTopic.checkDeleteTopicOnSite();
    }
}
