package SiteTestClasses;

import Helper.AdditionalMethods;
import Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Profile extends SetDriver {

    // страница регистрации
    private By loginButton = By.xpath("//*[@class='b-header__login-word']");
    private By emailInputFocused = By.xpath("//*[@class='i-control g-input g-input_sber b-login__input_email g-input_auth g-input_focused']/input");
    private By usernameInput = By.xpath("//*[@class='i-control g-input g-input_sber b-login__input_name g-input_auth']/input");
    private By passwordInput = By.xpath("i-control g-input g-input_sber b-login__input_password g-input_auth");
    private By checkboxPersonalDataCheck = By.xpath("//*[@class= 'i-control g-checkbox g-checkbox_sber b-login__personal-data-check i-control_sber']/div/div/div");
    private By checkboxOfferCheck = By.xpath("//*[@class='i-control g-checkbox g-checkbox_sber b-login__offer-check i-control_sber']/div/div/div");
    private By confirmButton = By.xpath("//*[@class='i-control g-button g-button_sber g-button_confirm-green-theme']");
    // попап c сообщением об отправке письма на почту о подтверждении регистрации
    private By closePopupConfirmRegistrationButton = By.xpath("/html/body/div[4]/div[2]/div/div[2]/div");
    //кнопка "Начать помогать" на стартовой
    private By startButton = By.xpath("//*[@class='b-start-block-empty__button']/a/span");
    //поиск в админке
    private By searchInput = By.xpath("//*[@id='DataTables_Table_0_filter']/label/input");
    //страница со списком пользователей
    private By emailInUsersList = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_type_string b-ap-table__field_email']/div");
    private By firstnameInUsersList = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_type_string b-ap-table__field_firstName']/div");
    private By lastnameInUsersList = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_type_string b-ap-table__field_lastName']/div");
    private By editUserButton = By.xpath("//*[@class='i-control b-ap-icon b-ap-icon_sberAdmin b-ap-icon_admin material-icons left']");
    //страница пользователя в админке
    private By firstnameOnUserPageInAdmin = By.xpath("//*[@id='firstName']");
    private By lastnameOnUserPageInAdmin = By.xpath("//*[@id='lastName']");
    private By emailOnUserPageInAdmin = By.xpath("//*[@id='email']");
    private By saveButtonOnUserPage = By.xpath("//*[@cla    ss='i-control b-ap-button b-ap-button_sberAdmin b-ap-button_admin']/button");
    //ошибки валадиции на странице пользователя в админке
    private By validationEmailError = By.xpath("//*[@id='email-error']/div");
    private By validationFirstnameError = By.xpath("//*[@id='firstName-error']/div");
    //переход в профиль на сайте
    private By userAvatarInHeader = By.xpath("//*[@class='i-control b-user-avatar b-user-avatar_sber b-user-avatar_type_initials']");
    private By initialsOnAvatarInHeader = By.xpath("//*[@class='i-control b-user-avatar b-user-avatar_sber b-user-avatar_type_initials']");
    private By editProfileButtonInDropdown = By.xpath("//*[@class='g-list__item g-list__item_profileEdit']");
    //профиль
    private By titleProfileInHeader = By.xpath("//*[@class='b-header__choice-phrase']");
    private By firstnameInputInProfile = By.xpath("//*[@class='b-profile-edit__first-name-input']/div/input");
    private By lastnameInputInProfile = By.xpath("//*[@class='b-profile-edit__last-name-input']/div/input");
    private By emailInProfile = By.xpath("//*[@class='g-input__input g-input__input_filled g-input__input_disabled']");
    private By saveButtonInProfile = By.xpath("//*[@class='b-profile-edit__button']/div");
    private By validationErrorInProfile = By.xpath("//*[@class='g-input__error-message-box']");
    private By backButton = By.cssSelector(".b-form__buttons.row > div:nth-child(1) > a");

    public void clickOnEditUserButton() {
        driver.findElement(editUserButton).click();
    }

    public void clickOnSaveButtonOnUserPage(){driver.findElement(saveButtonOnUserPage).click();}

    public void clickOnBackButton() {
        driver.findElement(backButton).click();
    }

    public String getTextOnStartButton() {
        return driver.findElement(startButton).getText();
    }

    public String getFirstnameInUsersList() {
        return driver.findElement(firstnameInUsersList).getText();
    }

    public String getLastnameInUsersList() {
        return driver.findElement(lastnameInUsersList).getText();
    }

    public String getEmailInUsersList() {
        return driver.findElement(emailInUsersList).getText();
    }

    public String getFirstnameOnUserPageInAdmin() {
        return driver.findElement(firstnameOnUserPageInAdmin).getAttribute("value");
    }

    public String getEmailOnUserPageInAdmin() {
        return driver.findElement(emailOnUserPageInAdmin).getAttribute("value");
    }

    public String getLastnameOnUserPageInAdmin() {
        return driver.findElement(lastnameOnUserPageInAdmin).getAttribute("value");
    }

    public String getTitleProfileInHeader(){
        return driver.findElement(titleProfileInHeader).getText();
    }

    public String getTextValidationErrorInProfile(){
        return driver.findElement(validationErrorInProfile).getText();
    }

    public String getFirstnameInProfile(){
        return driver.findElement(firstnameInputInProfile).getAttribute("value");
    }

    public String getLastnameInProfile(){
        return driver.findElement(lastnameInputInProfile).getAttribute("value");
    }

    public String getEmailInProfile() {
        return driver.findElement(emailInProfile).getAttribute("value");
    }

    public String getInitialsOnAvatarInHeader() {
        return driver.findElement(initialsOnAvatarInHeader).getText();
    }

    public String getValidationEmailError() {
        return driver.findElement(validationEmailError).getText();
    }

    public String getValidationFirstnameError() {
        return driver.findElement(validationFirstnameError).getText();
    }

    public void registration(String email, String firstname){
        AdditionalMethods methods = new AdditionalMethods();

        driver.findElement(loginButton).click();
        driver.findElement(emailInputFocused).sendKeys(email);
        driver.findElement(usernameInput).sendKeys(firstname);
        driver.findElement(checkboxPersonalDataCheck).click();
        driver.findElement(checkboxOfferCheck).click();
        driver.findElement(confirmButton).click();
        methods.Wait(1500);
        driver.findElement(closePopupConfirmRegistrationButton).click();
    }

    public void searchUserInAdmin(String email){
        driver.findElement(searchInput).click();
        driver.findElement(searchInput).sendKeys(email);
    }

    public void openProfileOnSite(){
        driver.findElement(userAvatarInHeader).click();
        driver.findElement(editProfileButtonInDropdown).click();
    }

    public void checkValidationInProfileOnSite(){
        driver.findElement(firstnameInputInProfile).clear();
        driver.findElement(saveButtonInProfile).click();
    }

    public void editProfile(String firstname, String lastname){
        driver.findElement(firstnameInputInProfile).sendKeys(firstname);
        driver.findElement(lastnameInputInProfile).sendKeys(lastname);
        driver.findElement(saveButtonInProfile).click();
    }

    public void checkValidationEmailOnUserPageInAdmin(String email){
        driver.findElement(emailOnUserPageInAdmin).clear();
        driver.findElement(emailOnUserPageInAdmin).sendKeys(email);
        driver.findElement(saveButtonOnUserPage).click();
    }

    public void checkValidationInputsOnUserPageInAdmin(){
        driver.findElement(emailOnUserPageInAdmin).clear();
        driver.findElement(firstnameOnUserPageInAdmin).clear();
        driver.findElement(saveButtonOnUserPage).click();
    }

    public void editUserPageInAdmin(String firstname, String lastname){
        driver.findElement(firstnameOnUserPageInAdmin).clear();
        driver.findElement(lastnameOnUserPageInAdmin).clear();
        driver.findElement(firstnameOnUserPageInAdmin).sendKeys(firstname);
        driver.findElement(lastnameOnUserPageInAdmin).sendKeys(lastname);
    }
}
