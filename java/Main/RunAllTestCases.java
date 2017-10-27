package Main;

import Helper.SetDriver;
import Helper.Settings;
import SiteTestClasses.Landing;
import SiteTestClasses.RegistrationConfirm;
import SiteTestClasses.StartPage;
import Tests.Admin.AdminDirectionTest;
import Tests.Admin.AdminFundTest;
import Tests.Admin.AdminTopicTest;
import Tests.Site.LandingTest;
import Tests.Site.ProfileTest;
import Tests.Site.RegistrationConfirmTest;
import Tests.Site.StartPageTest;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.io.IOException;
import java.util.ArrayList;

public class RunAllTestCases {
    public static void main(String[] args) throws Exception {
        Settings settings;
        settings = new Settings();

        ArrayList<Class> adminClasses = new ArrayList<>();
        adminClasses.add(AdminDirectionTest.class);
        adminClasses.add(AdminTopicTest.class);
        adminClasses.add(AdminFundTest.class);


        ArrayList<Class> siteClasses = new ArrayList<>();
        siteClasses.add(LandingTest.class);
        siteClasses.add(StartPageTest.class);
        siteClasses.add(ProfileTest.class);
        siteClasses.add(RegistrationConfirmTest.class);

        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        if (settings.testsSelection().equals("admin") || settings.testsSelection().equals("Admin")) {
            for (int i = 0; i < adminClasses.size(); i++) {
                junit.run(adminClasses.get(i));
            }
        } else if (settings.testsSelection().equals("site") || settings.testsSelection().equals("Site")) {
            for (int i = 0; i < siteClasses.size(); i++) {
                junit.run(siteClasses.get(i));
            }
        } else if (settings.testsSelection().equals("all") || settings.testsSelection().equals("All")) {
            for (int i = 0; i < adminClasses.size(); i++) {
                junit.run(adminClasses.get(i));
            }
            for (int i = 0; i < siteClasses.size(); i++) {
                junit.run(siteClasses.get(i));
            }
        } else {
            throw new Exception("Invalid string in the method \"testsSelection\"");
        }
    }
}