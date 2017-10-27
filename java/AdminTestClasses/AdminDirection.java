package AdminTestClasses;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import Helper.Settings;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class AdminDirection extends SetDriver{
    private AdminTopic adminTopic;
    private AdditionalMethods methods;

    private By tabDirection = By.xpath("//a[@href='/admin/entity/direction']");
    private By tabTopic = By.xpath("//a[@href='/admin/entity/topic']");
    private By selectedTopic = By.xpath("//*[@class='b-input__radio-buttons']/p[1]/label");
    //private By topicWithDirectionOnSite = By.xpath("//*[text()= "+getTopicWithDirection()+"]");
    private By directionInTopicOnSite = By.xpath("//*[text()='TitleDirectionNew']");
    private By idTdfInAdmin = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]/div");
    private By errorNotFoundPage = By.cssSelector(".b-not-found-page__error");
    private By startPageButtonOnErrorPage = By.cssSelector(".b-not-found-page__button > div");
    private By titleDirectionOnSite = By.xpath("//*[@class='b-header__choice-phrase']");
    private By descriptionDirectionOnSite = By.xpath("//*[@class='b-card-page__description']/div");
    private By moreDescriptionButton = By.cssSelector(".b-card-page__expand-button");
    private By firstLinkInDescriptionDirection = By.cssSelector(".b-card-page__full-text > a:nth-child(2)");
    private By secondLinkInDescriptionDirection = By.cssSelector(".b-card-page__full-text > a:nth-child(4)");
    private By addDirectionButtonOnSite = By.cssSelector(".b-card-page__card-info");
    private By selectedTopicWithDirectionOnSite = By.xpath("//*[@class='i-control b-dropdown-card b-dropdown-card_sber b-dropdown-card_hoverable b-dropdown-card_selected']/div/div[1]/div/div/div[2]/div[1]");
    private By topicWithDirectionInAdmin = By.cssSelector("#DataTables_Table_0 > tbody > tr > td.b-ap-table__field.b-ap-table__field_type_tree.b-ap-table__field_topics > div > div");
    private By deleteButtonOnEditDirectionPage = By.cssSelector(".b-form__buttons.row > div:nth-child(3) > a");
    private By errorInSelectTopicBlock = By.cssSelector("#selectedTopic-error > div");

   /* public String getTopicWithDirection() {
        adminTopicTest = new AdminTopicTest(driver);
        adminTopicTest.clickOnAddTdfButton();
        String str1 = driver.findElement(selectedTopic).getText();
        System.out.print(str1);
        adminTopicTest.clickOnBackButton();
        return str1;
    }*/

    public void clickOnTabDirection() {
        driver.findElement(tabDirection).click();
    }

    public void clickOnTabTopic() {
        driver.findElement(tabTopic).click();
    }

    public void clickOnMoreDescriptionButton(){driver.findElement(moreDescriptionButton).click();}

    public void clickOnFirstLinkInDescription(){
        driver.findElement(firstLinkInDescriptionDirection).click();
    }

    public void clickOnSecondLinkInDescription(){
        driver.findElement(secondLinkInDescriptionDirection).click();
    }

    public void clickOnAddDirectionButtonOnSite(){
        driver.findElement(addDirectionButtonOnSite).click();
    }

    public void clickOnSelectedTopicOnSite(){
        driver.findElement(selectedTopicWithDirectionOnSite).click();
    }

    public void clickOnStartPageButtonOnErrorPage(){
        driver.findElement(startPageButtonOnErrorPage).click();
    }

    public String getIdTdf() {
        return driver.findElement(idTdfInAdmin).getText();
    }

    public String getErrorNotFoundPage() {
        return driver.findElement(errorNotFoundPage).getText();
    }

    public String getTitleTdfOnSite() {
        return driver.findElement(titleDirectionOnSite).getText();
    }

    public String getDescriptionTdfOnSite() {
        return driver.findElement(descriptionDirectionOnSite).getText();
    }

    public String getTopicWithDirectionInAdmin() {
        return driver.findElement(topicWithDirectionInAdmin).getText();
    }

    public String getTitleTopicWithDirectionOnSite() {
        return driver.findElement(selectedTopicWithDirectionOnSite).getText();
    }

    public String getTitleDirectionInTopicOnSite() {
        return driver.findElement(directionInTopicOnSite).getText();
    }

    public String getErrorInSelectTopicBlock() {
        return driver.findElement(errorInSelectTopicBlock).getText();
    }

    public void addNewDirectionAndCheckValidation(String titleDirection, String descriptionDirection,String errorMessageInTitle,
                                                  String errorMessageInDescription, String errorInSelectTopicBlock, String errorMessageInFileInput) {
        methods = new AdditionalMethods();
        adminTopic = new AdminTopic();

        adminTopic.clickOnAddTdfButton();
        adminTopic.clickOnCreateTdfButton();
        Assert.assertEquals(adminTopic.getErrorMessageInTitle(),errorMessageInTitle);
        Assert.assertEquals(adminTopic.getErrorMessageInDescription(),errorMessageInDescription);
        Assert.assertEquals(getErrorInSelectTopicBlock(),errorInSelectTopicBlock);
        methods.Wait(1000);
        adminTopic.addTitleTdfInput(titleDirection);
        adminTopic.addDescriptionTdfInput(descriptionDirection);
        adminTopic.downloadLogoTdf();
        methods.imageDownload("jpg");
        methods.Wait(1000);
        driver.findElement(selectedTopic).click();
        adminTopic.clickOnCreateTdfButton();
        Assert.assertEquals(adminTopic.getErrorMessageInFileInput(),errorMessageInFileInput);
        adminTopic.downloadLogoTdf();
        methods.imageDownload("png");
        methods.Wait(1000);
        adminTopic.clickOnCreateTdfButton();
    }

    public void editAndPublishTdf(String newTitleTdf, String newDescriptionTdf) {
        adminTopic = new AdminTopic();

        adminTopic.clickOnEditTdfButton();
        adminTopic.addTitleTdfInput(newTitleTdf);
        adminTopic.addDescriptionTdfInput(newDescriptionTdf);
        adminTopic.clickOnPublishCheckboxTdf();
    }

    public void deleteDirection(){
        methods = new AdditionalMethods();
        adminTopic.clickOnEditTdfButton();
        //cкроллим страницу вниз
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,1000)", "");
        driver.findElement(deleteButtonOnEditDirectionPage).click();
        adminTopic.clickOnNoInConfirmDeletePopup();
        driver.findElement(deleteButtonOnEditDirectionPage).click();
        adminTopic.clickOnYesInConfirmDeletePopup();
    }

    public void checkPublishedTdf() {
        if (adminTopic.getSwitchStatus().equals("Нет No")) {
            adminTopic.clickOnEditTdfButton();
            adminTopic.clickOnPublishCheckboxTdf();
            adminTopic.saveChanges();
        }
    }
}
