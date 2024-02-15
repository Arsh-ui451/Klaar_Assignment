import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        //Navigate to Workspace settings page
        workSpaceSettings.navigateToWorkspaceSettings(driver);
        driver.findElement(By.xpath(klaarObjects.SELECT_USER_LIST)).click();

        driver.findElement(By.xpath("//*[text()='Custom Fields']")).click();
        driver.findElement(By.xpath("//*[text()='Add Field']")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='fieldName']")).sendKeys("NameABCD");
        driver.findElement(By.xpath("//*[@data-icon='down']")).click();
        driver.findElement(By.xpath("//*[text()='Date']")).click();
        driver.findElement(By.xpath("//*[@data-cy='modal-submit-button']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//*[text()='Add Field']")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='fieldName']")).sendKeys("Name12");
        driver.findElement(By.xpath("//*[@data-icon='down']")).click();
        driver.findElement(By.xpath("//*[text()='List']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Option List']")).sendKeys("Option1");
        driver.findElement(By.xpath("//*[text()=' Add another Item ']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Option List']")).sendKeys("Option2");
        driver.findElement(By.xpath("//*[text()=' Add another Item ']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Option List']")).sendKeys("Option3");
        driver.findElement(By.xpath("//*[@data-cy='modal-submit-button']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[@class='ant-switch ant-switch-checked']")).click();
        driver.findElement(By.xpath("//*[@data-icon='delete']")).click();
        driver.quit();
    }
}
