import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class addNewUser {
    public static void main(String[] args) {
        // Test 2: Add a new User

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

        //Navigate to Workspace settings page
        workSpaceSettings.navigateToWorkspaceSettings(driver);
        driver.findElement(By.xpath(klaarObjects.SELECT_USER_LIST)).click();

        //Verify landing on All Users Page - check visiblity of All Users header
       String actualText = driver.findElement(By.xpath(klaarObjects.ALL_USERS)).getText();
       String expectedText = "All Users";
       workSpaceSettings.verifyElement(actualText, expectedText);

       //Add user
        driver.findElement(By.xpath("//*[text()='Add']")).click();
        driver.findElement(By.xpath("//*[text()='Add User']")).click();

        //Create user
        String baseName = generateRandomName();
        driver.findElement(By.xpath(klaarObjects.Name)).sendKeys(baseName);
        String randomEmail = generateRandomEmail();
        driver.findElement(By.xpath(klaarObjects.Email)).sendKeys(randomEmail);
        WebElement element = driver.findElement(By.xpath("//button[normalize-space(.)='Add Now']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        //Verify landing on Edit User page
        String actualText1 = driver.findElement(By.xpath("//*[text()='Edit User']")).getText();
        String expectedText1 = "Edit User";
        workSpaceSettings.verifyElement(actualText1, expectedText1);
        driver.findElement(By.xpath("//i[@nztype='arrow-left']\n")).click();

        //close the browser
        driver.quit();
    }
    public static String generateRandomName() {
        String baseName = "FieldName";
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return baseName + randomNumber;
    }
    public static String generateRandomEmail() {
        String baseEmail = "FieldEmail";
        String domain = "@gmail.com";
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        return baseEmail + randomNumber + domain;
    }
}
