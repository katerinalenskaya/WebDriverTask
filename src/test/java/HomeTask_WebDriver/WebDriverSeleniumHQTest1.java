package HomeTask_WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverSeleniumHQTest1 {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup () {
        driver = new ChromeDriver();
    }

    @Test(description = "Task1")
    public void fillRequiredFields() throws InterruptedException {

        driver.get("https://paste.ubuntu.com/");
        //new WebDriverWait(driver, 10)
             //   .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"w0\"]")));

        WebElement textInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("content")));
        textInput.sendKeys("Hello from WebDriver");

        Select selectDropdown = new Select(driver.findElement(By.name("expiration")));
        selectDropdown.selectByVisibleText("A week");

        WebElement nameInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("id_poster")));
        nameInput.sendKeys("helloweb");

       Assert.assertTrue (textInput.isDisplayed(), "Your text is not displayed!");


    }
    @Test(description = "Task2")
    public void pasteBashCode() throws InterruptedException {

        driver.get("https://paste.ubuntu.com/");

        String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";

        WebElement textInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("content")));
        textInput.sendKeys(code);

        Select selectDropdown = new Select(driver.findElement(By.id("id_syntax")));
        selectDropdown.selectByVisibleText("Bash");

        Select selectCodeDropdown = new Select(driver.findElement(By.name("expiration")));
        selectCodeDropdown.selectByVisibleText("A week");

        WebElement nameInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("id_poster")));
        nameInput.sendKeys("how to gain dominance among developers");

        WebElement pasteButton = driver.findElement(By.xpath("//input[@value=\"Paste!\"]"));
        pasteButton.click();

       new WebDriverWait(driver,20)
               .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"title\"]")));

       String actualCode = driver.findElement(By.xpath("//*/td/div[@class='paste']")).getText();

        Assert.assertTrue (actualCode.contains(code), "Wrong Code display!");
    }


    @AfterMethod(alwaysRun = true)
    public void browserClose () {
        driver.quit();
        driver=null;
    }
}


