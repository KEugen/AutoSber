/**
 * Created by kelta.
 */

package Tests.Site;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.RegistrationConfirm;
import org.junit.Test;
import org.testng.Assert;

public class RegistrationConfirmTest extends SetDriver {

    @Test
    public void RegistrationConfirm () {
        GetUrl getUrl = new GetUrl();
        RegistrationConfirm registrationConfirm = new RegistrationConfirm(driver);
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#list?category=directions");
        methods.Wait(1000);

        registrationConfirm.userAddDirectionBeforeRegistration();

        //take text with number of funds from pop-up and write to the string
        String firstNumberOfFundsBeforeRegistration = registrationConfirm.getTextFromPopUpWithNumberOfFunds();
        //cut out anything except digits
        String firstResultNumberOfFundsBeforeRegistration = firstNumberOfFundsBeforeRegistration.replaceAll("[^0-9]", "");
        //System.out.println(firstResultNumberOfFundsBeforeRegistration);
        methods.Wait(1000);

        registrationConfirm.checkRegistrationWithOldUser("alexeysorsa@gmail.com", "Робогномиквчепчике");
        methods.Wait(500);
        Assert.assertEquals(registrationConfirm.getRegistrationErrorTextUserAlreadyExist(), "Пользователь с такой почтой уже существует");

        registrationConfirm.checkRegistrationPage();
        Assert.assertEquals(registrationConfirm.getRegistrationEmailAdressFromEmailInput(), "alexeysorsa@gmail.com");

        registrationConfirm.checkRegistrationWithEmptyFields();
        Assert.assertEquals(registrationConfirm.getRegistrationErrorTextAfterClear(), "Необходимо заполнить все поля");

        registrationConfirm.checkRegistrationTermsOfUseLink();

        registrationConfirm.checkRegistrationPublicOfferLink();

        registrationConfirm.checkRegistrationWithIncorrectEmail("test", "Робогномиквчепчике");
        Assert.assertEquals(registrationConfirm.getRegistrationErrorTextIncorrectEmail(),"Введён некорректный адрес электронной почты");

        //generate email for using in registration and authorisation tests
        String email = methods.generateRandomEmail();

        registrationConfirm.checkRegistrationWithIncorrectCheckbox(email);
        Assert.assertEquals(registrationConfirm.getRegistrationErrorTextIncorrectCheckbox(),"Необходимо принять условия Пользовательского соглашения и Публичной оферты");

        registrationConfirm.checkRegistrationWithNewUser();

        //take text with number of funds from start page
        String secondNumberOfFundsBeforeRegistration = registrationConfirm.getTextFromStartPageWithNumberOfFunds();
        //cut out anything except digits
        String secondResultNumberOfFundsBeforeRegistration = secondNumberOfFundsBeforeRegistration.replaceAll("[^0-9]", "");
        //System.out.println(secondResultNumberOfFundsBeforeRegistration);

        //take text with number of directions from start page
        String thirdNumberOfFundsBeforeRegistration = registrationConfirm.getTextFromStartPageWithNumberOfDirections();
        //cut out anything except digits
        String thirdResultNumberOfFundsBeforeRegistration = thirdNumberOfFundsBeforeRegistration.replaceAll("[^0-9]", "");
        //System.out.println(thirdResultNumberOfFundsBeforeRegistration);

        //get digit with sum of finds and directions through concatenation
        String sumOfThirdAndSecondResultsBeforeRegistration = thirdResultNumberOfFundsBeforeRegistration + secondResultNumberOfFundsBeforeRegistration;
        //System.out.println(sumOfThirdAndSecondResultsBeforeRegistration);

        Assert.assertEquals(sumOfThirdAndSecondResultsBeforeRegistration, firstResultNumberOfFundsBeforeRegistration);
        //isFundsEquals(sumOfThirdAndSecondResultsBeforeRegistration, firstResultNumberOfFundsBeforeRegistration);

        registrationConfirm.checkLogout();
        Assert.assertEquals(registrationConfirm.getTextFromStartPageStartToHelpButton(),"Начать помогать");

        driver.get("https://temp-mail.ru/option/change");
        methods.Wait(2000);

        //String parentWindowId = driver.getWindowHandle();
        //final Set<String> oldWindowsSet = driver.getWindowHandles();

        registrationConfirm.confirmRegistrationFromEmail(methods.deleteDomainInEmail(email));

        driver.get(registrationConfirm.getTextFromConfirmLink());
        //registrationConfirm.clickOnTempMailConfirmLink();
        //driver.close();
        //methods.moveFocusToTheNewWindow(oldWindowsSet);

        registrationConfirm.setupIncorrectPasswordLength("123");
        Assert.assertEquals(registrationConfirm.getPasswordErrorTextIncorrectLength(),"Пароль должен быть не менее 6-ти символов");

        registrationConfirm.setupIncorrectPasswordMatch("123qwe", "qwe123");
        Assert.assertEquals(registrationConfirm.getPasswordErrorTextIncorrectMatch(),"Пароли не совпадают");

        registrationConfirm.setupIncorrectPasswordEmptyFields();
        Assert.assertEquals(registrationConfirm.getPasswordEmptyIncorrectText(),"Необходимо заполнить все поля");

        registrationConfirm.setupValidPassword("123qwe","123qwe");

        registrationConfirm.userAddFundAfterRegistration();

        //take text with number of funds from logo counter and write in string
        String firstNumberOfFundsAfterRegistration = registrationConfirm.getTextWithNumberOfFundsInLogoCounter();
        //cut out anything except digits
        String firstResultWithNumberOfFundsAfterRegistration = firstNumberOfFundsAfterRegistration.replaceAll("[^0-9]", "");
        //System.out.println(firstResultWithNumberOfFundsAfterRegistration);

        //take text with fund name at site fund page to compare him with name of fund at admin user page
        String nameOfFundAtFundPage = registrationConfirm.getTextFromTitleAtFundPage();
        //System.out.println(nameOfFundAtFundPage);

        registrationConfirm.userMoveToAuthorisationPage();

        registrationConfirm.checkAuthorisationWithIncorrectEmail(methods.generateRandomEmail(),"123qwe");
        Assert.assertEquals(registrationConfirm.getAuthorisationErrorTextIncorrectEmail(),"Не найден пользователь с такой почтой");

        registrationConfirm.checkAuthorisationWithEmptyFields();
        Assert.assertEquals(registrationConfirm.getAuthorisationErrorTextEmptyFields(), "Необходимо заполнить все поля");

        registrationConfirm.checkAuthorisationWithIncorrectPassword(email, "incorrectpass@$%^");
        Assert.assertEquals(registrationConfirm.getAuthorisationErrorTextIncorrectPassword(),"Неправильный пароль или адрес электронной почты");

        registrationConfirm.checkAuthorisationWithCorrectOldUser("123qwe");

        //after authorisation with old user we are check that new funds replace old funds (which user get during registration)
        String secondNumberOfFundsAfterRegistration = registrationConfirm.getTextFromStartPageWithNumberOfFunds();
        String secondResultWithNumberOfFundsAfterRegistration = secondNumberOfFundsAfterRegistration.replaceAll("[^0-9]","");
        Assert.assertEquals(firstResultWithNumberOfFundsAfterRegistration, secondResultWithNumberOfFundsAfterRegistration);
        //System.out.println(secondResultWithNumberOfFundsAfterRegistration);

        getUrl.getAdminUrlWithStr("admin/users/users");
        registrationConfirm.checkControlStatusAtAdminUserPage(email);
        //предполагается возврат true от вызываемой функции. Если возвращается false, то тест прекращается.
        Assert.assertEquals(registrationConfirm.getDisplayedNameFromAdminUserActiveEmailCheckbox(), true);

        registrationConfirm.checkUserFundListAtAdminUserPage();

        //take text with name of fund from admin user page and write in string
        String nameOfFundAtAdminUserPage = registrationConfirm.getTextFromNameOfFundAtAdminUserPage();
        //System.out.println(nameOfFundAtAdminUserPage);
        //compare received fund name at site and admin
        Assert.assertEquals(nameOfFundAtFundPage, nameOfFundAtAdminUserPage);
    }
}
