package org.festivalmusic.band;

import net.bytebuddy.build.Plugin;
import org.asynchttpclient.util.Assertions;
import org.festivalmusic.band.page.FestivalBandPage;
import org.festivalmusic.band.util.PropertyUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FestivalBandPageTest {

    private WebDriver webDriver;
    private FestivalBandPage festivalBandPage;
    private PropertyUtil propertyUtil = new PropertyUtil();
    private Map<String, String> bandWithFestivalNameMap;

    @BeforeTest
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        String url = propertyUtil.getProperty("application.url");
        if (url == null) {
            System.out.println("URL is Empty.");
        } else {
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @Test(priority = 1)
    public void getBandName() {
        festivalBandPage = new FestivalBandPage(webDriver);
        bandWithFestivalNameMap = festivalBandPage.getBandFestivalNames();
    }

    @Test(priority = 2)
    public void bandNameCheck() {
        String bandNames = propertyUtil.getProperty("bandName");
        if (bandNames != null) {
            String[] bandNamesAsArray = bandNames.split(",");
            Set<String> bandKeys = bandWithFestivalNameMap.keySet();
            for (String bandName : bandNamesAsArray) {
                if (!bandKeys.contains(bandName)) {
                    Assert.assertFalse("BandName is not valid " + bandName, false);
                }

            }
        }
    }

    @Test(priority = 3)
    public void festivalNameCheck() {
        String festivalName = propertyUtil.getProperty("festivalName");
        if (festivalName != null) {
            String[] festivalNamesAsArray = festivalName.split(",");
            Set<String> festKeys = bandWithFestivalNameMap.keySet();
            for (String festivalNam : festivalNamesAsArray) {
                if (!festKeys.contains(festivalNam)) {
                    Assert.assertFalse("Festival name is not valid " + festivalNam, false);
                }

            }
        }

    }

    @Test(priority = 4)
    public void bandFestivalMappingCheck() {
        String bandKey1 = propertyUtil.getProperty("bandName1.key");
        String bandValue1 = propertyUtil.getProperty("bandName1.value");
        String originalValue = bandWithFestivalNameMap.get(bandKey1);
        Assert.assertEquals("Band Name is correctly mapped with Festival Name", originalValue, bandValue1);
    }
}