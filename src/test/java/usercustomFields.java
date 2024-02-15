import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class usercustomFields {
    public static void main(String[] args) {
        // Test 3: User custom fields

        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kusha\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to Klaar website
        driver.get("https://dev.klaarhq.com");

        // Click on 'Login with klaar' button
        driver.findElement(By.xpath(klaarObjects.LOGIN_KLAAR_BUTTON)).click();

        // Assuming credentials are stored in variables
        String validUsername = "deepa.nayak@gamma.klaar.team";
        String validPassword = "Klaar2021";

        //logging with valid credentials
        workSpaceSettings.login(driver, validUsername, validPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Navigate to User List page
        workSpaceSettings.navigateToWorkspaceSettings(driver);
        driver.findElement(By.xpath(klaarObjects.SELECT_USER_LIST)).click();

        //Navigate to Custom List page
        driver.findElement(By.xpath(klaarObjects.ClickCustomList)).click();

        //In the user details page, add a future date in the custom field for the user and save.
        driver.findElement(By.xpath(klaarObjects.ADD_FIELD)).click();
        String baseName = addNewUser.generateRandomName();
        driver.findElement(By.xpath(klaarObjects.FieldName)).sendKeys(baseName);
        driver.findElement(By.xpath(klaarObjects.DropDownList)).click();
        driver.findElement(By.xpath(klaarObjects.SelectDate)).click();
        driver.findElement(By.xpath(klaarObjects.SubmitButton)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Add a new custom field of type List with 3 list options
        driver.findElement(By.xpath(klaarObjects.ADD_FIELD)).click();
        String fieldName = addNewUser.generateRandomName();
        driver.findElement(By.xpath(klaarObjects.FieldName)).sendKeys(fieldName);
        driver.findElement(By.xpath(klaarObjects.DropDownList)).click();
        driver.findElement(By.xpath(klaarObjects.SelectList)).click();
        driver.findElement(By.xpath(klaarObjects.OptionList)).sendKeys("Option1");
        driver.findElement(By.xpath(klaarObjects.AddAnotherItem)).click();
        driver.findElement(By.xpath(klaarObjects.OptionList)).sendKeys("Option2");
        driver.findElement(By.xpath(klaarObjects.AddAnotherItem)).click();
        driver.findElement(By.xpath(klaarObjects.OptionList)).sendKeys("Option3");
        driver.findElement(By.xpath(klaarObjects.SubmitButton)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Test the custom field switch on/off toggle and verify changes are reflected respectively in the user company details page
        //Delete the custom field and verify the custom field is no longer visible in the custom field table as well as the user company details page.
        String xpath = "//tr[@class='row ng-star-inserted']//td[normalize-space()='" + fieldName + "']//following-sibling::td//nz-switch/button";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        toggleButton.click();
        driver.findElement(By.xpath("//*[@data-icon='delete']")).click();
        driver.quit();
    }

}
