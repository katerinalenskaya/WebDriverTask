package first_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverSeleniumHQTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup () {
        driver = new ChromeDriver();
    }

    @Test (description = "Search results")
    public void commonSearchTermResultsAreNotEmpty() {

        driver.get("http://seleniumhq.org");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("gsc-i-id1")));

        WebElement searchInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("gsc-i-id1")));
        searchInput.sendKeys("selenium java");
        searchInput.sendKeys(Keys.ENTER);

        new WebDriverWait(driver, 20).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'gsc-webResult') and contains (., 'selenium') and contains (., 'java')]")));

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class,'gsc-webResult') and contains (., 'selenium') and contains (., 'java')]"));
        System.out.println("Search results number for requested term: " + searchResults.size());

        Assert.assertTrue (searchResults.size()>0, "Your test failed!!");
        }

        @AfterMethod (alwaysRun = true)
    public void browserClose () {
            driver.quit();
            driver=null;
        }
}
