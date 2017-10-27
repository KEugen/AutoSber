package Helper;

import org.openqa.selenium.WebDriver;

public class GetUrl extends SetDriver{
    private Settings settings = new Settings() ;

    public String choiceStand() {
        //settings = new Settings();
        String stand =  settings.setTypeStand();

        if (stand.equals("qa")) {
            String qa = "http://sber-vmeste.qa.lan/";
            return qa;
        }
        else {
            String demo = "https://demo.sberbankvmeste.ru/";
            return demo;
        }

    }

    // get url
    public void getSiteUrl() {
        driver.get(choiceStand());
    }
    public void getAdminUrl(){
        //settings = new Settings();
        String stand =  settings.setTypeStand();
        if (stand.equals("qa")) {
            driver.get("http://admin.sber-vmeste.qa.lan/");
        }
        else {
            driver.get("http://stay:alive@admin.demo.sberbankvmeste.ru/");
            driver.get("http:admin.demo.sberbankvmeste.ru/");
        }
    }

    // get url + str
    public void getSiteUrlWithStr(String str) {
        driver.get(choiceStand()+str);
    }
    public void getAdminUrlWithStr(String str){
        //settings = new Settings();

        String stand =  settings.setTypeStand();
        if (stand.equals("qa")) {
            driver.get("http://admin.sber-vmeste.qa.lan/" + str);
        }
        else {
            driver.get("http://stay:alive@admin.demo.sberbankvmeste.ru/" + str);
            driver.get("http:admin.demo.sberbankvmeste.ru/" + str);
        }
    }

    // get return str url
    public String getSiteUrlStr() {
        String str = choiceStand();
        return str;
    }

/*    public void driverGetCurrentAdminUrl(String str){driver.get(choiceAdminStand() + str); driver.get(choiceAdminStand() + str);
    }*/

/*    public String choiceAdminStand() {
        String stand =  setTypeStand();

        if (stand == "qa") {
            String qa = "http://admin.sber-vmeste.qa.lan/";
            return qa;
        }
        else {
            String demo = "http://stay:alive@admin.demo.sberbankvmeste.ru";
            return demo;

        }
    }*/
}
