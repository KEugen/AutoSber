package Tests.Site;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import Helper.SetDriver;
import SiteTestClasses.CardsPage;
import org.junit.Assert;
import org.junit.Test;

public class CardsPageTest extends SetDriver {

    @Test
    public void checkCardsPage() {
        GetUrl getUrl = new GetUrl();
        CardsPage cardsPage = new CardsPage();
        AdditionalMethods methods = new AdditionalMethods();

        getUrl.getSiteUrlWithStr("#card?id=106");
        methods.Wait(1000);

        Assert.assertTrue(cardsPage.clickOnAddButton());
    }


}
