package AdminTestClasses;

import Helper.AdditionalMethods;
import Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdminFund extends SetDriver{

    private AdminTopic adminTopic;
    private AdditionalMethods methods;

    private By tabFunds = By.xpath("//a[@href='/admin/entity/fund']");
    private By checkboxError = By.xpath("//*[@class='g-checkbox__error']");
    private By checkboxOpenButton = By.xpath("//*[@class='g-checkbox__icon-wrapper']/i[1]");
    private By checkboxCheckedDirection = By.cssSelector("form > div:nth-child(5) > div > div > ul > li:nth-child(1) > ul > li:nth-child(1) > label");
    private By titleDirectionWithFundInAdmin = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_type_tree b-ap-table__field_directions']/div/div");
    private By titleTopicWithDirectionInAdmin = By.xpath("//*[@class='b-ap-table__field b-ap-table__field_type_tree b-ap-table__field_topics']/div/div");
    private By titleDirectionOnSite = By.xpath("//*[@class='b-card__title']");
    private By titleTopicInAdmin = By.xpath("//*[@class='g-checkbox__label']");

    public void clickOnTabFunds() {
        driver.findElement(tabFunds).click();
    }

    public String getCheckboxError() {
        return driver.findElement(checkboxError).getText();
    }

    public String getDirectionWithFundInAdmin() {
        return driver.findElement(titleDirectionWithFundInAdmin).getText();
    }

    public String getTitleTopicWithDirection() {
        return driver.findElement(titleTopicWithDirectionInAdmin).getText();
    }

    public String getTitleDirectionOnSite() {
        return driver.findElement(titleDirectionOnSite).getText();
    }

    public String getTitleTopicOnSite() {
        return driver.findElement(titleTopicInAdmin).getText();
    }

    public void addNewFundAndCheckValidation(String titleFund, String descriptionFund, String errorMessageInTitle,
                                             String errorMessageInDescription, String checkboxError, String errorMessageInFileInput) {

        methods = new AdditionalMethods();
        adminTopic = new AdminTopic();

        adminTopic.clickOnAddTdfButton();
        adminTopic.clickOnCreateTdfButton();
        Assert.assertEquals(adminTopic.getErrorMessageInTitle(),errorMessageInTitle);
        Assert.assertEquals(adminTopic.getErrorMessageInDescription(),errorMessageInDescription);
        Assert.assertEquals(getCheckboxError(),checkboxError);
        methods.Wait(1000);
        adminTopic.addTitleTdfInput(titleFund);
        adminTopic.addDescriptionTdfInput(descriptionFund);
        adminTopic.downloadLogoTdf();
        methods.imageDownload("jpg");
        methods.Wait(1000);
        driver.findElement(checkboxOpenButton).click();
        driver.findElement(checkboxCheckedDirection).click();
        adminTopic.clickOnCreateTdfButton();
        Assert.assertEquals(adminTopic.getErrorMessageInFileInput(),errorMessageInFileInput);
        adminTopic.downloadLogoTdf();
        methods.imageDownload("png");
        methods.Wait(1000);
        adminTopic.clickOnCreateTdfButton();
    }
}
