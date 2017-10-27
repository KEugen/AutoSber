/**
 * Created by kelta
 *
 * Test Suite Description
 *
 * Переходим на страницу #list?category=directions, добавляем в ЮФ направление,фонды, подтверждаем попап, нажимаем на кнопку “Войти” в хэдере
 * Вводим имя и email существующего пользователя,отмечаем чекбоксы,нажимаем на кнопку “Продолжить”
 * Нажимаем на кнопку “Войти”
 * Нажимаем на крестики в полях Имя и email
 * Нажимаем на ссылки  "Пользовательского соглашения” и “Публичной офертой о заключении договора пожертвования"
 * Вводим имя, отмечаем чекбоксы и некорректный email
 * Вводим имя, корректный email, не отмечаем чекбоксы
 * Вводим имя и новый email, отмечаем чекбоксы, нажимаем на кнопку “Продолжить”
 * Нажимаем на иконку профиля в хэдере, в дропдауне нажимаем на кнопку “Выйти
 * Проверяем подтверждение регистрации: переходим на почте по ссылке с подтверждением регистрации
 * Вводим некорректное количество символов, несовпадающие пароли
 * Нажимаем на крестики в полях ввода пароля
 * Вводим корректный пароль,нажимаем на кнопку “Сохранить”
 * Набираем др. направления и фонды в ЮФ,в хэдере нажимаем на кнопку “Войти”
 * В окне авторизации вводим некорректный email и пароль нового пользователя,нажимаем на кнопку ”Сохранить”
 * Нажимаем на крестики в поле email и пароля
 * Вводим корректный email нового пользователя и некорректный пароль ,нажимаем на кнопку ”Сохранить”
 * Вводим корректный email и пароль нового пользователя,нажимаем на кнопку ”Сохранить”
 * Проверяем пункт 3 Пользователь в админке: проверяем корректность изменения состояния переключателя с  подтвержденным email пользователя
 * Проверяем пункт 5 Пользователь в админке: В поле "Состав ЮФ" нажимаем на кнопку “Раскрыть”
 */

package SiteTestClasses;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.Set;



public class RegistrationConfirm extends SetDriver {
    public WebDriver driver;
    public GetUrl getUrl;

    //funds page variables and registration page variables
    private By directionDropdownButton = By.xpath("//*[@class='b-card-list__cards']/div[1]/descendant::div[@class='b-dropdown-card__directions']");
    private By directionDropdownItemButton = By.xpath("//*[@class='g-dropdown__content']/div[1]/descendant::div[@class='b-dropdown-card-item__checkbox-icon']");
    private By popupConfirmButton = By.xpath("/html/body/div[4]/div[2]/div/div[3]");
    private By loginHeaderButton = By.xpath("//*[@class='b-header__login-word']");
    private By registrationEmailInput = By.cssSelector(".b-login__inputs > div:nth-child(1) > div > input");
    private By registrationNameInput = By.cssSelector(".b-login__inputs > div:nth-child(2) > div > input");
    private By registrationFirstCheckbox = By.cssSelector(".b-login__agreement > div:nth-child(1) > div > div");
    private By registrationSecondCheckbox = By.cssSelector(".b-login__agreement > div:nth-child(2) > div > div");
    private By registrationConfirmButton = By.xpath("//*[text()='Продолжить']");
    private By registrationErrorTextUserAlreadyExist = By.xpath("//*[@class='b-login__error-block']/div");
    private By loginErrorButton = By.xpath("//*[@class='b-login__error-button']");
    private By registrationTitleButton = By.xpath("//*[@class='b-auth__title-button']");
    private By registrationEmailClearButton = By.cssSelector(".b-login__inputs > div:nth-child(1) > div > div.g-input__clear-button");
    private By registrationNameClearButton = By.cssSelector(".b-login__inputs > div:nth-child(2) > div > div.g-input__clear-button");
    private By registrationErrorTextAfterClear = By.xpath("//*[@class='b-login__error-block']/div");
    private By registrationTermsOfUseLink = By.cssSelector(".b-login__agreement > div:nth-child(1) > a");
    private By registrationPublicOfferLink = By.cssSelector(".b-login__agreement > div:nth-child(2) > a");
    private By registrationFirstCheckboxActive = By.cssSelector(".b-login__agreement > div:nth-child(1) > div > div > .g-checkbox__icon-wrap > .g-checkbox__icon-checked");
    private By registrationSecondCheckboxActive = By.cssSelector(".b-login__agreement > div:nth-child(2) > div > div > .g-checkbox__icon-wrap > .g-checkbox__icon-checked");
    private By registrationErrorTextIncorrectEmail = By.xpath("//*[@class='b-login__error-block']/div");
    private By registrationErrorTextCheckboxOff = By.xpath("//*[@class='b-login__error-block']/div");
    private By registrationPopupCloseButton = By.xpath("/html/body/div[4]/div[2]/div/div[2]/div");
    private By popupConfirmDirections = By.xpath("/html/body/div[4]/div[2]/div/div[2]");
    private By startPageNumberOfFunds = By.xpath("//*[@class='b-start-block__funds-count']");
    private By startPageNumberOfDirections = By.xpath("//*[@class='b-start-block__directions-count']");
    private By headerAvatarUserButton = By.xpath("//*[@class='b-header__user-avatar']");
    private By headerLogoutUserButton = By.xpath("//*[@class='g-list__item g-list__item_logout']");

    //variables for operations with funds after registration
    private By startPageStartToHelpButton = By.xpath("//*[@class='b-start-block-empty__button']");
    private By fundsPageFundTabButton = By.xpath("//*[@class='g-tab__tab']");
    private By fundsPageFundButton = By.cssSelector(".b-list-page__tab > div > div:nth-child(3) > div > div > a:nth-child(1) > div");
    private By fundPageAddButton = By.xpath("//*[@class='b-card-page__card-button']");
    private By fundPageHeaderLogoButton = By.xpath("//*[@class='b-header__heart-together-wrapper']");
    private By fundPageLogoFundCounter = By.xpath("//*[@class='b-header__funds-counter']");
    private By fundPageTitle = By.xpath("//*[@class='b-header__choice-phrase']");

    //var's for authorisation operations
    private By authorisationTitleLoginButton = By.xpath("//*[@class='b-auth__title-button']");
    private By authorisationEmailInput = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[4]/div/div[1]/div[1]/div/input");
    private By authorisationPasswordInput = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[4]/div/div[1]/div[2]/div/input");
    private By authorisationEnterButton = By.xpath("//*[@class='b-auth__confirm-button']");
    private By authorisationErrorTextIncorrectEmail = By.xpath("//*[@class='b-login__error-block']");
    private By authorisationEmailClearButton = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[4]/div/div[1]/div[1]/div/div[2]/div");
    private By authorisationPasswordClearButton = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[4]/div/div[1]/div[2]/div/div[2]/div");
    private By authorisationErrorTextEmptyFields = By.xpath("//*[@class='b-login__error-block']");
    private By authorisationErrorTextIncorrectPassword = By.xpath("//*[@class='b-login__error-block']");

    //temp-mail variables
    private By tempMailLoginInput = By.xpath("//*[@class='col-sm-10']/input");
    private By tempMailSubmitButton = By.xpath("//*[@id='postbut']");
    private By tempMailLogoButton = By.xpath("//*[@class='col-md-2_1 col-lg-2']/a");
    private By tempMailOpenLetterButton = By.xpath("//*[@class='glyphicon glyphicon-chevron-right']");
    private By tempMailConfirmLink = By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[3]/div/div/center/table/tbody/tr/td/table/tbody/tr/td/center/table/tbody/tr[2]/th/div/div/p/a[2]");

    //setup password page variables
    private By setupPasswordInput = By.cssSelector(".b-login__new-pass-set > div:nth-child(2) > div > input");
    private By setupPasswordInputConfirm = By.cssSelector(".b-login__new-pass-set > div:nth-child(3) > div > input");
    private By setupPasswordLengthIncorrectText = By.xpath("//*[@class='b-login__error-block']");
    private By setupPasswordMatchIncorrectText = By.xpath("//*[@class='b-login__error-block']");
    private By setupPasswordFirstInputClearButton = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[5]/div/div[2]/div[2]/div/div[2]/div");
    private By setupPasswordSecondInputClearButton = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div/div/div[5]/div/div[2]/div[3]/div/div[2]/div");
    private By setupPasswordEmptyIncorrectText = By.xpath("//*[@class='b-login__error-block']");
    private By setupPasswordSaveButton = By.xpath("//*[@class='b-auth__confirm-button']");
    private By setupPasswordSuccessButton = By.xpath("//*[@class='b-auth__confirm-button']");

    //admin page variables
    private By adminSearchUserInput = By.xpath("//*[@id=\"DataTables_Table_0_filter\"]/label/input");
    private By adminUsersPageEditButton = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[1]/div/div/a/i");
    private By adminUserPageActiveCheckbox = By.xpath("//input[@type='checkbox' and @checked='']");
    private By adminUserPageFundsDropdownButton = By.xpath("//*[@class='g-dropdown-sber__opener dropdown-button btn']");
    private By adminUserPageNameOfFund = By.cssSelector(".g-dropdown-sber__content > div > div > a > div:nth-child(2)");

    public RegistrationConfirm(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextFromPopUpWithNumberOfFunds(){
        return driver.findElement(popupConfirmDirections).getText();
    }

    public String getRegistrationErrorTextUserAlreadyExist(){
        return driver.findElement(registrationErrorTextUserAlreadyExist).getText();
    }

    public String getRegistrationEmailAdressFromEmailInput(){
        return driver.findElement(registrationEmailInput).getAttribute("value");
    }

    public String getRegistrationErrorTextAfterClear(){
        return driver.findElement(registrationErrorTextAfterClear).getText();
    }

    public String getRegistrationErrorTextIncorrectEmail(){
        return driver.findElement(registrationErrorTextIncorrectEmail).getText();
    }

    public String getRegistrationErrorTextIncorrectCheckbox(){
        return driver.findElement(registrationErrorTextCheckboxOff).getText();
    }

    public String getTextFromStartPageWithNumberOfFunds(){
        return driver.findElement(startPageNumberOfFunds).getText();
    }

    public String getTextFromStartPageWithNumberOfDirections(){
        return driver.findElement(startPageNumberOfDirections).getText();
    }

    public String getTextFromStartPageStartToHelpButton(){
        return driver.findElement(startPageStartToHelpButton).getText();
    }

    public String getPasswordErrorTextIncorrectLength(){
        return driver.findElement(setupPasswordLengthIncorrectText).getText();
    }

    public String getPasswordErrorTextIncorrectMatch(){
        return driver.findElement(setupPasswordMatchIncorrectText).getText();
    }

    public String getPasswordEmptyIncorrectText(){
        return driver.findElement(setupPasswordEmptyIncorrectText).getText();
    }

    public String getTextWithNumberOfFundsInLogoCounter(){
        return driver.findElement(fundPageLogoFundCounter).getText();
    }

    public String getAuthorisationErrorTextIncorrectEmail(){
        return driver.findElement(authorisationErrorTextIncorrectEmail).getText();
    }

    public String getAuthorisationErrorTextEmptyFields() {
        return driver.findElement(authorisationErrorTextEmptyFields).getText();
    }

    public String getTextFromConfirmLink() {
        return driver.findElement(tempMailConfirmLink).getText();
    }

    public String getAuthorisationErrorTextIncorrectPassword (){
        return driver.findElement(authorisationErrorTextIncorrectPassword).getText();
    }

    public String getTextFromTitleAtFundPage(){
        return driver.findElement(fundPageTitle).getText();
    }

    public String getTextFromNameOfFundAtAdminUserPage(){
        return driver.findElement(adminUserPageNameOfFund).getText();
    }

    //if in element was found property "checked" then function return true. If not founded then function return false.
    public boolean getDisplayedNameFromAdminUserActiveEmailCheckbox(){
        try {
            return driver.findElement(adminUserPageActiveCheckbox).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void userAddDirectionBeforeRegistration(){
        driver.findElement(directionDropdownButton).click();
        driver.findElement(directionDropdownItemButton).click();
        driver.findElement(popupConfirmButton).click();
    }

    public void checkRegistrationWithOldUser(String email, String firstname){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(loginHeaderButton).click();
        methods.Wait(1500);
        driver.findElement(registrationEmailInput).sendKeys(email);
        driver.findElement(registrationNameInput).sendKeys(firstname);
        driver.findElement(registrationFirstCheckbox).click();
        driver.findElement(registrationSecondCheckbox).click();
        driver.findElement(registrationConfirmButton).click();
        methods.Wait(1000);
    }

    public void checkRegistrationPage(){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(loginErrorButton).click();
        methods.Wait(1000);
    }

    public void checkRegistrationWithEmptyFields(){
        AdditionalMethods methods = new AdditionalMethods();;

        driver.findElement(registrationTitleButton).click();
        methods.Wait(500);
        driver.findElement(registrationEmailClearButton).click();
        driver.findElement(registrationNameClearButton).click();
        driver.findElement(registrationEmailInput).click();
        driver.findElement(registrationEmailInput).sendKeys(Keys.ENTER);
    }

    public void checkRegistrationTermsOfUseLink(){
        AdditionalMethods methods = new AdditionalMethods();;

        //saving state of page
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(registrationTermsOfUseLink).click();

        //moving focus to the new page
        methods.moveFocusToTheNewWindow(oldWindowsSet);

        Assert.assertEquals(driver.getCurrentUrl(), "https://sberbankvmeste.ru/files/personal.pdf");

        //close page and moving back to the old page
        driver.close();
        driver.switchTo().window(parentWindowId);
        methods.Wait(500);

    }

    public void checkRegistrationPublicOfferLink(){
        AdditionalMethods methods = new AdditionalMethods();

        //saving state of page
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(registrationPublicOfferLink).click();

        //moving focus to the new page
        methods.moveFocusToTheNewWindow(oldWindowsSet);

        Assert.assertEquals(driver.getCurrentUrl(), "https://sberbankvmeste.ru/files/offer.pdf");

        //close page and moving back to the old page
        driver.close();
        driver.switchTo().window(parentWindowId);

        methods.Wait(500);
    }

    public void checkRegistrationWithIncorrectEmail(String email, String firstname){
        driver.findElement(registrationEmailInput).click();
        driver.findElement(registrationEmailInput).sendKeys(email);
        driver.findElement(registrationNameInput).sendKeys(firstname);
        driver.findElement(registrationNameInput).sendKeys(Keys.ENTER);
    }

    public void checkRegistrationWithIncorrectCheckbox(String email){
        AdditionalMethods methods = new AdditionalMethods();;

        driver.findElement(registrationEmailClearButton).click();
        driver.findElement(registrationEmailInput).sendKeys(email);
        methods.Wait(500);
        driver.findElement(registrationFirstCheckboxActive).click();
        driver.findElement(registrationSecondCheckboxActive).click();
        driver.findElement(registrationNameInput).sendKeys(Keys.ENTER);
        //System.out.println(email);
    }

    public void checkRegistrationWithNewUser(){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(registrationFirstCheckbox).click();
        driver.findElement(registrationSecondCheckbox).click();
        driver.findElement(registrationConfirmButton).click();
        methods.Wait(1500);
        driver.findElement(registrationPopupCloseButton).click();
        methods.Wait(500);
    }

    public void checkLogout(){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(headerAvatarUserButton).click();
        driver.findElement(headerLogoutUserButton).click();
    }

    public void confirmRegistrationFromEmail(String email) {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(tempMailLoginInput).click();
        driver.findElement(tempMailLoginInput).sendKeys(email);
        driver.findElement(tempMailSubmitButton).click();
        driver.findElement(tempMailLogoButton).click();
        driver.navigate().refresh();
        driver.findElement(tempMailOpenLetterButton).click();
        methods.Wait(2000);
    }

    public void setupIncorrectPasswordLength (String passwordIncorrectLength){
        AdditionalMethods methods = new AdditionalMethods();

        methods.Wait(1500);
        driver.findElement(setupPasswordInput).click();
        driver.findElement(setupPasswordInput).sendKeys(passwordIncorrectLength);
        driver.findElement(setupPasswordInputConfirm).click();
        driver.findElement(setupPasswordInputConfirm).sendKeys(passwordIncorrectLength);
        driver.findElement(setupPasswordInputConfirm).sendKeys(Keys.ENTER);
    }

    public void setupIncorrectPasswordMatch (String passwordMatchFirst, String passwordMatchConfirm){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(setupPasswordInput).click();
        driver.findElement(setupPasswordInput).sendKeys(passwordMatchFirst);
        driver.findElement(setupPasswordInputConfirm).click();
        driver.findElement(setupPasswordInputConfirm).sendKeys(passwordMatchConfirm);
        driver.findElement(setupPasswordInputConfirm).sendKeys(Keys.ENTER);
    }

    public void setupIncorrectPasswordEmptyFields () {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(setupPasswordFirstInputClearButton).click();
        driver.findElement(setupPasswordSecondInputClearButton).click();
        driver.findElement(setupPasswordInput).click();
        driver.findElement(setupPasswordInput).sendKeys(Keys.ENTER);
    }

    public void setupValidPassword(String passwordValidFirst, String passwordValidConfirm){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(setupPasswordInput).click();
        driver.findElement(setupPasswordInput).sendKeys(passwordValidFirst);
        driver.findElement(setupPasswordInputConfirm).click();
        driver.findElement(setupPasswordInputConfirm).sendKeys(passwordValidConfirm);
        methods.Wait(500);
        driver.findElement(setupPasswordSaveButton).click();
        methods.Wait(2000);
        driver.findElement(setupPasswordSuccessButton).click();
        methods.Wait(2000);
    }

    public void userAddFundAfterRegistration() {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(startPageStartToHelpButton).click();
        methods.Wait(800);
        driver.findElement(fundsPageFundTabButton).click();
        driver.findElement(fundsPageFundButton).click();
        methods.Wait(2000);
        driver.findElement(fundPageAddButton).click();
        methods.Wait(1000);
    }

    public void userMoveToAuthorisationPage() {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(fundPageHeaderLogoButton).click();
        methods.Wait(2000);
        driver.findElement(loginHeaderButton).click();
        driver.findElement(authorisationTitleLoginButton).click();
    }

    public void checkAuthorisationWithIncorrectEmail(String incorrectEmail, String correctPass) {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(authorisationEmailInput).sendKeys(incorrectEmail);
        driver.findElement(authorisationPasswordInput).click();
        driver.findElement(authorisationPasswordInput).sendKeys(correctPass);
        driver.findElement(authorisationEnterButton).click();
        methods.Wait(2000);
    }

    public void checkAuthorisationWithEmptyFields() {
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(authorisationEmailClearButton).click();
        driver.findElement(authorisationPasswordClearButton).click();
        driver.findElement(authorisationEmailInput).click();
        driver.findElement(authorisationEmailInput).sendKeys(Keys.ENTER);
    }

    public void checkAuthorisationWithIncorrectPassword(String correctEmail, String incorrectPass){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(authorisationEmailInput).sendKeys(correctEmail);
        driver.findElement(authorisationPasswordInput).sendKeys(incorrectPass);
        driver.findElement(authorisationPasswordInput).sendKeys(Keys.ENTER);
        methods.Wait(1000);
    }

    public void checkAuthorisationWithCorrectOldUser(String correctPass){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(authorisationPasswordClearButton).click();
        driver.findElement(authorisationPasswordInput).sendKeys(correctPass);
        driver.findElement(authorisationEnterButton).click();
        methods.Wait(2000);
    }

    public void checkControlStatusAtAdminUserPage (String userEmail){
        AdditionalMethods methods = new AdditionalMethods();
        getUrl = new GetUrl();

        //getUrl.getAdminUrlWithStr("admin/users/users");
        methods.Wait(10000);
        driver.findElement(adminSearchUserInput).click();
        driver.findElement(adminSearchUserInput).sendKeys(userEmail);
        methods.Wait(500);
        driver.findElement(adminUsersPageEditButton).click();
        methods.Wait(2500);
    }

    public void checkUserFundListAtAdminUserPage (){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(adminUserPageFundsDropdownButton).click();
        methods.Wait(1000);
    }
}