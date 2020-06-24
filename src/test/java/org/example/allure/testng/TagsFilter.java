package org.example.allure.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TagsFilter {

    @Test(description = "Tags filter check")
    void testTag(){
        System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://demo.realworld.io");

        //Getting 3rd tag
        By sidebarTag2 = By.cssSelector(".sidebar > .tag-list a:nth-of-type(3)");
        By activeTab = By.cssSelector(".nav-link.active.ng-binding");
        By tagsOnPosts = By.cssSelector(".article-preview > .preview-link > ul");

        wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarTag2));

        WebElement tag2 = driver.findElement(sidebarTag2);
        String tag2name = tag2.getText();
        tag2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));

        WebElement tab = driver.findElement(activeTab);
        String tabName = tab.getText();

        Assert.assertEquals(tag2name, tabName, "Tag not matched");

        wait.until(ExpectedConditions.visibilityOfElementLocated(tagsOnPosts));

        List<WebElement> postsTags = driver.findElements(tagsOnPosts);
        System.out.println(tag2name);
        for (WebElement tag : postsTags)
        {
            System.out.println(tag.getText());
            Assert.assertTrue(tag.getText().contains(tag2name), "Tag not matched");
        }

        driver.close();

    }

}
