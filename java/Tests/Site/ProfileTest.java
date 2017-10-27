/**
 переходим на стартовую страницу на сайте
 записываем в строку сгенерированный email
 регистрируем нового пользователя
 попадаем на стартовую, сравниваем кнопку Начать помогать
 переходим в админку на страницу со списком пользователей во вкладке Пользователи
 вводим в поиск email зарегистрированного пользователя
 на старнице со списком пользователей сравниваем email и имя нового пользователя
 нажимаем на кнопку редактировать, переходим на страницу редактирования пользователя в админке
 сравниваем email и имя пользователя
 переходим на сайт
 сравниваем инициалы на аватаре в хэдере и переходим в профиль
 сравниваем заголовок в хэдере на странице профиля, url, имя и email
 проверяем появление корректной ошибки валидации в профиле на сайте: удаляем имя пользователя, сохраняем
 редактируем имя пользователя и добавляем фамилию, сохраняем
 попадаем на стартовую, переходим обратно в профиль и проверяем, что изменения применились и на аватаре сравниваем инициалы
 переходим в админку на страницу со списком пользователей, вводим в поиск email пользователя
 сравниваем новое имя и фамилию пользователя на странице со списком пользователей
 переходим на страницу редактирования профиля пользователя в админке
 сравниваем новое имя и фамилию юзера на странице редактирования профиля в админке
 проверяем появление корректных ошибок валидации на странице пользователя в админке:
 - изменяем email на email существующего пользователя,сохраняем
 - изменяем email на невалидный, сохраняем
 - удаляем email и имя, сохраняем
 редактируем админом страницу пользователя в админке, изменяем имя и фамилию
 не сохраняем, а нажимаем на кнопку назад, попадаем на страницу со списком пользователей
 вводим в поиск email пользователя
 проверяем,что измененеия не применились на странице со списком пользователей
 переходим на страницу редактирования профиля пользователя в админке
 проверяем,что измененеия не применились на странице редактирования профиля в админке
 редактируем админом страницу пользователя в админке и сохраняем изменения, попадаем на страницу со списком пользователей
 вводим в поиск email пользователя, проверяем, что отображается новая фамилия и имя
 переходим на страницу профиля на сайте и проверяем,что изменения применились на сайте
 */
package Tests.Site;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.Profile;
import org.junit.Test;
import org.testng.Assert;

public class ProfileTest extends SetDriver {

    @Test
    public void profile() {
        GetUrl getUrl = new GetUrl();
        Profile profile = new Profile();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#start");
        String email = methods.generateRandomEmail();
        String firstname = "Testname";

        //регистрируемся
        profile.registration(email,firstname);
        Assert.assertEquals(profile.getTextOnStartButton(),"Начать помогать");

        //переходим в админку
        getUrl.getAdminUrlWithStr("admin/users/users");
        methods.Wait(2000);

        //вводим в поиск email зарегистрированного пользователя
        profile.searchUserInAdmin(email);

        //сравниваем email и имя пользователя
        Assert.assertEquals(profile.getFirstnameInUsersList(),firstname);
        Assert.assertEquals(profile.getLastnameInUsersList(),"");
        Assert.assertEquals(profile.getEmailInUsersList(),email);

        // переходим на страницу редактирования профиля пользователя в админке
        profile.clickOnEditUserButton();
        methods.Wait(500);

        //сравниваем email и имя пользователя
        Assert.assertEquals(profile.getFirstnameOnUserPageInAdmin(),firstname);
        Assert.assertEquals(profile.getEmailOnUserPageInAdmin(),email);
        Assert.assertEquals(profile.getLastnameOnUserPageInAdmin(),"");

        //переходим на сайт
        getUrl.getSiteUrlWithStr("#start");

        //сравниваем инициалы на аватаре и переходим в профиль
        Assert.assertEquals(profile.getInitialsOnAvatarInHeader(),"T");
        profile.openProfileOnSite();
        methods.Wait(500);

        //сравниваем заголовок в хэдере на странице профиля, url, имя и email
        Assert.assertEquals(profile.getTitleProfileInHeader(),"Редактирование профиля");
        Assert.assertEquals(driver.getCurrentUrl(),getUrl.getSiteUrlStr()+"#profile");
        Assert.assertEquals(profile.getEmailInProfile(),email);
        Assert.assertEquals(profile.getFirstnameInProfile(),firstname);
        Assert.assertEquals(profile.getLastnameInProfile(),"");

        //проверка валидации в профиле
        profile.checkValidationInProfileOnSite();
        Assert.assertEquals(profile.getTextValidationErrorInProfile(),"Это поле не может быть пустым");
        String newFirstname = "Newtestname";
        String newLastname = "Testlastname";

        //редактируем профиль
        profile.editProfile(newFirstname,newLastname);
        methods.Wait(1500);
        getUrl.getSiteUrlWithStr("#profile");

        // проверяем, что изменения применились на сайте
        Assert.assertEquals(profile.getInitialsOnAvatarInHeader(),"NT");
        Assert.assertEquals(profile.getFirstnameInProfile(),newFirstname);
        Assert.assertEquals(profile.getLastnameInProfile(),newLastname);

        //переходим в админку
        getUrl.getAdminUrlWithStr("admin/users/users");
        profile.searchUserInAdmin(email);

        // сравниваем новое имя и фамилию пользователя на странице со списком пользователей
        Assert.assertEquals(profile.getFirstnameInUsersList(),newFirstname);
        Assert.assertEquals(profile.getLastnameInUsersList(),newLastname);

        // переходим на страницу редактирования профиля пользователя в админке
        profile.clickOnEditUserButton();
        methods.Wait(500);

        //сравниваем новое имя и фамилию юзера на странице редактирования профиля в админке
        Assert.assertEquals(profile.getFirstnameOnUserPageInAdmin(),newFirstname);
        Assert.assertEquals(profile.getLastnameOnUserPageInAdmin(),newLastname);

        //проверяем валидацию на странице пользователя в админке
        profile.checkValidationEmailOnUserPageInAdmin("kirill@yopmail.com");
        Assert.assertEquals(profile.getValidationEmailError(),"Пользователь с такой почтой уже существует");
        profile.checkValidationEmailOnUserPageInAdmin("invalidemail");
        Assert.assertEquals(profile.getValidationEmailError(),"Введён некорректный адрес электронной почты");
        profile.checkValidationInputsOnUserPageInAdmin();
        Assert.assertEquals(profile.getValidationEmailError(),"Поле обязательно для заполнения");
        Assert.assertEquals(profile.getValidationFirstnameError(),"Поле обязательно для заполнения");
        String newFirstnameAdmin = "Newtestname";
        String newLastnameAdmin = "Testlastname";

        //редактируем админом страницу пользователя в админке
        profile.editUserPageInAdmin(newFirstnameAdmin,newLastnameAdmin);

        //проверяем,что изменения не применяются по кнопке "Назад" в админке
        profile.clickOnBackButton();
        profile.searchUserInAdmin(email);

        // проверяем,что измененеия не применились на странице со списком пользователей
        Assert.assertEquals(profile.getFirstnameInUsersList(),newFirstname);
        Assert.assertEquals(profile.getLastnameInUsersList(),newLastname);

        // переходим на страницу редактирования профиля пользователя в админке
        profile.clickOnEditUserButton();
        methods.Wait(500);

        // проверяем,что измененеия не применились на странице редактирования профиля в админке
        Assert.assertEquals(profile.getFirstnameOnUserPageInAdmin(),newFirstname);
        Assert.assertEquals(profile.getLastnameOnUserPageInAdmin(),newLastname);

        // редактируем админом страницу пользователя в админке и сохраняем изменения
        profile.editUserPageInAdmin(newFirstnameAdmin,newLastnameAdmin);
        profile.clickOnSaveButtonOnUserPage();
        profile.searchUserInAdmin(email);

        //сравниваем  имя и фамилию пользователя на странице со списком пользователей
        Assert.assertEquals(profile.getFirstnameInUsersList(),newFirstnameAdmin);
        Assert.assertEquals(profile.getLastnameInUsersList(),newLastnameAdmin);

        // переходим на страницу профиля на сайте и проверяем,что изменения применились на сайте
        getUrl.getSiteUrlWithStr("#profile");
        Assert.assertEquals(profile.getFirstnameInProfile(),newFirstnameAdmin);
        Assert.assertEquals(profile.getLastnameInProfile(),newLastnameAdmin);
    }
}
