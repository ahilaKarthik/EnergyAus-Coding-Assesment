package org.festivalmusic.band.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class FestivalBandPage {

    private WebDriver webDriver;

    @FindBy(xpath = "/html/body/form/ol/li")
    private List<WebElement> bandNames;

    public FestivalBandPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }
    
    public Map<String,String> getBandFestivalNames(){
        Map<String,String> bandFestivalNamMap = new LinkedHashMap<>();
        for(int index=1;index<=bandNames.size();index++){
            String bandWithFestivalName = webDriver.findElement(By.xpath("/html/body/form/ol/li["+index+"]/ul/li/../..")).getText();
            if(bandWithFestivalName !=null){
                String[] keyValues = bandWithFestivalName.split("\n");
                if(keyValues.length==2){
                    bandFestivalNamMap.put(keyValues[0],keyValues[1]);
                }else{
                    bandFestivalNamMap.put(keyValues[0],"");
                }

            }
        }
        return bandFestivalNamMap;
    }


}
