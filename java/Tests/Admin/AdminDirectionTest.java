/**
 - открываем admin.sber-vmeste.qa.lan или demo.sberbankvmeste.ru
 - переходим на таб Направление
 - addNewDirectionAndCheckValidation:
 нажимаем на кнопку добавления нового тнф("плюсик")
 на странице создания тнф нажимаем на кнопку "Создать", проверяем ошибки валидации в поле "Заголовок","Описание",блок с добавлением Темы
 добавляем заголовок TitleDirection, описание DescriptionDirection,
 нажимаем на кнопку загрузки картинки, загружаем картинку с форматом jpg
 выбираем первую тему в списке
 не выбираем чекбокс "Опубликован", нажимаем на кнопку создания темы "Создать"
 проверяем ошибку валидации о неверном формате картинки
 нажимаем на кнопку загрузки картинки, загружаем картинку с форматом png
 нажимаем на кнопку создания темы "Создать"
 - searchTdf: вводим в поиск название новой темы и проверяем. что переключатель выключен(не опубликовано)
 - записываем в строку id направления
 - переходим на страницу направления по id (#card?id="+idDirection+)
 - проверяем, что отображается 404 страница
 - переходим обратно в админку в таб "Направления"
 - searchTopic: находим новое направление в списке направлений
 - editAndPublishTdf:
 на странице со списком направлений нажимаем на кнопку редактирования направления
 изменяем название темы (New)
 изменяем описание направления
 нажимаем на чебокс "Опубликован"
 - saveChanges: нажимаем на кнопку "Сохранить" на странице редакт направления
 - searchTdf: находим отредактированное направление в списке направлений
 - проверяем, что в списке направлений отображается отредактированный заголовок направления
 - проверяем, что в списке направлений отображается включенный переключатель(Опубликована)
 - editAndPublishTdf:изменяем заголовок и описание
 - clickOnBackButton: не сохраняем отредактированную тему, а нажимаем на кнопку "Назад"
 - searchTdf
 - проверяем, что отображается старый заголовок и по кнопке "Назад" изменения не применились
 - записываем в строку заголовок темы. в которую входит направление
 - нажимаем на таб Темы
 - ищем тему, к которой принадлежит направление
 - checkPublishedTdf():
 проверяем статус переключетяля у темы, если "Нет", то нажимаем на редактирование темы,кликаем на кнопку Опубликовать,сохраняем изменения
 - переходит на сайт по id направления
 - проверяем новый заголовок и описание до кнопки Подробнее
 - нажимаем на кнопку Подробнее и сравниваем все описание
 - запоминаем текущую вкладку, нажимаем на первую ссылку в описании
 - переводим фокус на вторую вкладку и сравниваем url,закрываем вторую вкладку и переводим фокус на 1ую
 - нажимаем на вторую ссылку в описании
 - закрываем вкладку , переводим фокус на 1 вкладку
 - нажимаем на кнопку добавления направления
 - переходим на страницу со списком тем на сайте
 - сравниваем зеленую тему на сайте, с темой, к которой принадлежит направление, в админке
 - нажимаем на зеленую тему
 - сравниваем заголовок зеленого(выбранного) направления с заголовком направления в админке
 (проверили,что направление отображается в нужной теме)
 - переходим в админку, нажимаем на таб Направления
 - находим направление
 - deleteDirection():
 нажимаем на кнопку редактирования направления, нажимаем на кнопку Удалить
 в попапе с подтверждением удаления нажимаем на Нет
 нажимаем на кнопку Удалить
 в попапе с подтверждением удаления нажимаем на Да
 - в поиске ищем направление
 - проверяем,что направеление не найдено и  отображается текст No matching records found
 - переходим на сайт по id направления
 - проверяем, что отображается 404 страница
 - нажимаем на  кнопку На главную на 404 странице
 - проверяем, что произошел преход на стартовую страницу
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AdminDirectionTest extends SetDriver {

    @Test
    public void checkDirection() {

    GetUrl getUrl = new GetUrl();
    AdditionalMethods methods = new AdditionalMethods();
    AdminTopic adminTopic = new AdminTopic();
    AdminDirection adminDirection = new AdminDirection();

    getUrl.getAdminUrl();
    adminDirection.clickOnTabDirection();
    // getTopicWithDirection();
    adminDirection.addNewDirectionAndCheckValidation(
            "TitleDirection",
            "DescriptionDirection",
            "Поле обязательно для заполнения",
            "Поле обязательно для заполнения",
            "Необходимо выбрать тему, к которой относится направление",
            "Формат файла должен быть в PNG");

    adminTopic.searchTdf("TitleDirection");
    Assert.assertEquals(adminTopic.getSwitchStatus(),"Нет No");
    String idDirection = adminDirection.getIdTdf();
    getUrl.getSiteUrlWithStr("#card?id="+idDirection+"");
    //проверка 404 страницы
    Assert.assertEquals(adminDirection.getErrorNotFoundPage(),"404");
    getUrl.getAdminUrlWithStr("admin/entity/direction");
    adminTopic.searchTdf("TitleDirection");
    adminDirection.editAndPublishTdf("New"," Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
            " Aenean commodo ligula eget dolor. Aenean ma\n" +
            "1. Cайт фонда Хабенского: [url]http://bfkh.ru/[/url] \n" +
            "2. [url=Cайт фонда Хабенского]http://bfkh.ru/[/url]");
    methods.Wait(1000);
    adminTopic.saveChanges();
    //проверяем, что изменения применились в админке
    adminTopic.searchTdf("TitleDirectionNew");
    Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TitleDirectionNew");
    Assert.assertEquals(adminTopic.getSwitchStatus(),"Да Yes");
    //проверяем,что изменения не сохраняются при нажатии на кнопку "Назад"
    adminDirection.editAndPublishTdf("1","1");
    adminTopic.clickOnBackButton();
    adminTopic.searchTdf("TitleDirectionNew");
    Assert.assertEquals(adminTopic.getTitleTdfInListAdmin(),"TitleDirectionNew");
    String titleTopicWithDirectionInAdmin = adminDirection.getTopicWithDirectionInAdmin();
    //проверяем чекбокс Опубликован у темы с новым направлением
    adminDirection.clickOnTabTopic();
    adminTopic.searchTdf(titleTopicWithDirectionInAdmin);
    adminDirection.checkPublishedTdf();
    //проверяем,что изменения применились на сайте
    getUrl.getSiteUrlWithStr("#card?id="+idDirection+"");
    methods.Wait(1000);
    Assert.assertEquals(adminDirection.getTitleTdfOnSite(),"TitleDirectionNew");
    Assert.assertEquals(adminDirection.getDescriptionTdfOnSite(),"DescriptionDirection Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
            " Aenean commodo ligula eget dolor.\n" +
            "\n" +
            "подробнее");
    adminDirection.clickOnMoreDescriptionButton();
    Assert.assertEquals(adminDirection.getDescriptionTdfOnSite(),"DescriptionDirection Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
            " Aenean commodo ligula eget dolor. Aenean ma\n" +
            "1. Cайт фонда Хабенского: http://bfkh.ru/\n" +
            "2. Cайт фонда Хабенского");

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
    //проверяем, что направление входит в выбранную в админке тему
    methods.Wait(1000);
    adminDirection.clickOnAddDirectionButtonOnSite();
    methods.Wait(1000);
    getUrl.getSiteUrlWithStr("#list?category=directions");
    driver.navigate().refresh();
    methods.Wait(1000);
    Assert.assertEquals(adminDirection.getTitleTopicWithDirectionOnSite(),titleTopicWithDirectionInAdmin);
    adminDirection.clickOnSelectedTopicOnSite();
    Assert.assertEquals(adminDirection.getTitleDirectionInTopicOnSite(),"TitleDirectionNew");
    getUrl.getAdminUrl();
    adminDirection.clickOnTabDirection();
    adminTopic.searchTdf("TitleDirectionNew");
    //проверяем удаление темы
    adminDirection.deleteDirection();
    adminTopic.searchTdf("TitleDirectionNew");
    Assert.assertEquals(adminTopic.getNotFoundTopicInSearch(),"No matching records found");
    //проверка удаления темы на сайте
    getUrl.getSiteUrlWithStr("#card?id="+idDirection+"");
    Assert.assertEquals(adminDirection.getErrorNotFoundPage(),"404");
    adminDirection.clickOnStartPageButtonOnErrorPage();
    methods.Wait(1000);
    Assert.assertEquals(driver.getCurrentUrl(),getUrl.getSiteUrlStr()+ "#start");
    }
}
