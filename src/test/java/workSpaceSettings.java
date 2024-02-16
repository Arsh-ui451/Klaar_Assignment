import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.xml.xpath.XPath;
import java.util.concurrent.TimeUnit;

public class workSpaceSettings {
    public static void main(String[] args) {
        // Test 1: Workspace Settings

        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kusha\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to Klaar website
        driver.get("https://dev.klaarhq.com");

        // Verify the existence of the 'Login with klaar' button
        String actualButtonText = driver.findElement(By.xpath(klaarObjects.LOGIN_KLAAR_BUTTON)).getText();
        String expectedButtonText = "Log in with klaar";
        verifyElement(actualButtonText, expectedButtonText);

        // Click on 'Login with klaar' button
        driver.findElement(By.xpath(klaarObjects.LOGIN_KLAAR_BUTTON)).click();

        // Assuming credentials are stored in variables
        String validUsername = "deepa.nayak@gamma.klaar.team";
        String validPassword = "Klaar2021";
        String invalidUsername = "invalid_username@gmail.com";
        String invalidPassword = "invalid_password";

        // Negative case: logging with invalid credentials
        login(driver, invalidUsername, invalidPassword);
        WebElement usernameField = driver.findElement(By.id("email-field"));
        WebElement passwordField = driver.findElement(By.id("password-field"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", usernameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
        String actualErrorText = driver.findElement(By.xpath("//*[@id=\"main-app\"]/app-root/app-sign-in/nz-spin/div/div[1]/div[2]/div/app-alerts/div/p")).getText();
        String expectedErrorText = "Sorry! It seems like your user details are not added in Klaar. Please contact your HR.";
        verifyElement(actualErrorText, expectedErrorText);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Positive case: logging with valid credentials
        login(driver, validUsername, validPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Verify the existence of the 'Setting' module
        String settingButtonText = driver.findElement(By.xpath(klaarObjects.SETTINGS_BUTTON)).getText();
        String expectedText = "Settings";
        verifyElement(settingButtonText, expectedText);

        //Navigate to Workspace settings page
        navigateToWorkspaceSettings(driver);

        //Confirm landing to Workspace settings page
        confirmLandingOnWorkspaceSettingsPage(driver);

        //Validate Workspace settings page
        validateWorkspaceSettingsPage(driver);

        //Add new workspace logo and confirm the successfull deletion
        WebElement element1 = driver.findElement(By.xpath(klaarObjects.CHOOSE_FILE_BUTTON));
        scrolltoElement(driver, element1);

        //upload file
        driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys("C:\\Users\\kusha\\IdeaProjects\\Klaar_Assignment\\src\\main\\resources\\Shin_Chan.jpg");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //save file
        WebElement element2 = driver.findElement(By.xpath(klaarObjects.SAVE_FILE_BUTTON));
        scrolltoElement(driver, element2);
        driver.findElement(By.xpath(klaarObjects.SAVE_FILE_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        //delete file and verifying successfull deletion
        WebElement element3 = driver.findElement(By.xpath(klaarObjects.DELETE_FILE_BUTTON));
        scrolltoElement(driver, element3);
        driver.findElement(By.xpath(klaarObjects.DELETE_FILE_BUTTON)).click();
        driver.findElement(By.xpath("//*[@id=\"main-app\"]/app-root/app-layout/nz-layout/nz-layout/nz-content/div/app-settings/div/nz-card[3]/div/div/nz-spin/div/div/div/button[2]/p")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement element4 = driver.findElement(By.xpath(klaarObjects.CHOOSE_FILE_BUTTON));
        scrolltoElement(driver, element4);
        String fileText = driver.findElement(By.xpath(klaarObjects.CHOOSE_FILE_BUTTON)).getText();
        String expectedText1 = "Choose File";
        verifyElement(fileText, expectedText1);

        //close the browser
        driver.quit();
        
    }
    public static void login(WebDriver driver, String username, String password) {
        WebElement usernameField = driver.findElement(By.id("email-field"));
        WebElement passwordField = driver.findElement(By.id("password-field"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public static void verifyElement(String actualElement, String expectedElement){
        assert actualElement.equals(expectedElement);

    }

    public static void navigateToWorkspaceSettings(WebDriver driver) {
        driver.findElement(By.xpath(klaarObjects.SETTINGS_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void confirmLandingOnWorkspaceSettingsPage(WebDriver driver) {
        String workspaceSettingsTitle = driver.findElement(By.xpath(klaarObjects.WORKSPACE_SETTING_TITLE)).getText();
        if (workspaceSettingsTitle.equals("Workspace Settings")) {
            System.out.println("Test 1: Workspace Settings - Passed");
        } else {
            System.out.println("Test 1: Workspace Settings - Failed");
        }
    }
    public static void validateWorkspaceSettingsPage(WebDriver driver) {
        // Validate appearance
        // Check if the page title is correct
        // Check if the logo is displayed
        assert driver.findElement(By.className("mb-0 org-logo-header")).isDisplayed() : "Workspace logo is not displayed";
        // Check if input fields are present
        assert driver.findElement(By.className("tw-mt-3 tw-h-14 tw-rounded-md ant-input ant-input-lg ng-pristine ng-valid ng-touched")).isDisplayed() : "Workspace name input field is not displayed";
    }
    public static void scrolltoElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.scrollToElement(element).perform();
    }


    }
