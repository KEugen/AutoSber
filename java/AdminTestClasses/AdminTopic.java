package AdminTestClasses;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdminTopic extends SetDriver{

    private AdditionalMethods methods;

    private By newTopicButton = By.xpath("//*[@class='i-control b-ap-table b-ap-table_sberAdmin b-ap-table_admin']/div[2]/a/i");
    private By titleTopicInput = By.cssSelector("#title");
    private By descriptionTopicInput = By.xpath("//*[@class='input-field col s12']/textarea");
    private By publishedTopicCheckbox = By.xpath("//*[@class ='i-control b-input b-input_sberAdmin b-input_admin  b-input_type_checkbox']/div/label");
    private By downloadLogo = By.cssSelector("#picture");
    private By createTopicButton = By.cssSelector("button");
    private By topicInListOnSite = By.xpath("//div[1][text()='TestTitleTopic']");
    private By newTopicInListOnSite = By.xpath("//div[1][text()='TestTitleTopicNew']");
    private By searchInput = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_title']/input");
    private By editTopicButton = By.xpath("//*[@class='i-control b-ap-icon b-ap-icon_sberAdmin b-ap-icon_admin material-icons left']");
    private By saveButton = By.cssSelector("button");
    private By titleTopicInListAdmin = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[3]/div");
    private By backButton = By.cssSelector(".b-form__buttons.row > div:nth-child(1) > a");
    private By switchButton = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/div/div[2]");
    private By deleteTopicButton = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[1]/div/div[2]/a/i");
    private By noInConfirmDeletePopup = By.xpath("//*[@class='i-control g-modal g-modal_sberAdmin g-modal_admin']/div[2]/div/div[2]/a");
    private By yesInConfirmDeletePopup = By.xpath("//*[@class='i-control g-modal g-modal_sberAdmin g-modal_admin']/div[2]/div/div[1]/a");
    private By notFoundTopicInSearch = By.cssSelector("#DataTables_Table_0 > tbody > tr > td");
    private By errorMessageInTitle = By.cssSelector("#title-error");
    private By errorMessageInDescription = By.cssSelector("#description-error");
    private By errorMessageInFileInput = By.cssSelector("#picture-error");

    public String getTitleTdfInListAdmin() {
        return driver.findElement(titleTopicInListAdmin).getText();
    }

    public String getTitleNewTopicOnSite() {
        return driver.findElement(newTopicInListOnSite).getText();
    }

    public String getSwitchStatus() {
        return driver.findElement(switchButton).getAttribute("textContent");
    }

    public String getErrorMessageInTitle() {
        return driver.findElement(errorMessageInTitle).getText();
    }

    public String getErrorMessageInDescription() {
         return driver.findElement(errorMessageInDescription).getText();
    }

    public String getErrorMessageInFileInput() {
        return driver.findElement(errorMessageInFileInput).getText();
    }

    public String getNotFoundTopicInSearch() {
        return driver.findElement(notFoundTopicInSearch).getText();
    }

    public void checkTopicOnSite() {
        if (driver.findElements(topicInListOnSite).isEmpty() == false) {
            Assert.fail("Не работает чекбокс Опубликован в админке");
        }
    }

    public void checkDeleteTopicOnSite() {
        if (driver.findElements(newTopicInListOnSite).isEmpty() == false) {
            Assert.fail("Не работает удаление темы, тема отображается на сайте");
        }
    }

    public void clickOnAddTdfButton(){
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(newTopicButton).click();
    }

    public void clickOnCreateTdfButton(){
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(createTopicButton).click();
    }

    public void addTitleTdfInput(String titleTdf){
        driver.findElement(titleTopicInput).sendKeys(titleTdf);
    }

    public void addDescriptionTdfInput(String descriptionTdf){
        driver.findElement(descriptionTopicInput).sendKeys(descriptionTdf);
    }

    public void downloadLogoTdf(){
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(downloadLogo).click();
    }

    public void clickOnEditTdfButton(){
        driver.findElement(editTopicButton).click();
    }

    public void clickOnPublishCheckboxTdf(){
        driver.findElement(publishedTopicCheckbox).click();
    }

    public void clickOnDeleteTdfButton(){
        driver.findElement(deleteTopicButton).click();
    }

    public void clickOnNoInConfirmDeletePopup(){
        driver.findElement(noInConfirmDeletePopup).click();
    }

    public void clickOnYesInConfirmDeletePopup(){
        driver.findElement(yesInConfirmDeletePopup).click();
    }

    public void addNewTopicAndCheckValidation(String titleTopic, String descriptionTopic) {
        methods = new AdditionalMethods();

        clickOnAddTdfButton();
        clickOnCreateTdfButton();
        Assert.assertEquals(getErrorMessageInTitle(),"Поле обязательно для заполнения");
        Assert.assertEquals(getErrorMessageInDescription(),"Поле обязательно для заполнения");
        methods.Wait(1000);
        addTitleTdfInput(titleTopic);
        addDescriptionTdfInput(descriptionTopic);
        downloadLogoTdf();
        methods.imageDownload("jpg");
        methods.Wait(1000);
        clickOnCreateTdfButton();
        Assert.assertEquals(getErrorMessageInFileInput(),"Формат файла должен быть в PNG");
        downloadLogoTdf();
        methods.imageDownload("png");
        methods.Wait(1000);
        clickOnCreateTdfButton();
    }

    public void searchTdf(String titleTopic) {
        driver.findElement(searchInput).sendKeys(titleTopic);
    }

    public void editAndPublishTopic(String newTitleTopic) {
        clickOnEditTdfButton();
        addTitleTdfInput(newTitleTopic);
        clickOnPublishCheckboxTdf();
    }

    public void saveChanges() {
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(saveButton).click();
    }

    public void clickOnBackButton() {
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(backButton).click();
    }

    public void deleteTopic() {
        clickOnDeleteTdfButton();
        clickOnNoInConfirmDeletePopup();
        clickOnDeleteTdfButton();
        clickOnYesInConfirmDeletePopup();
    }
}

