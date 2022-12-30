package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.eq;

public class DriverQA {

    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void start(String parBrowser) {
        String title = "";
        try {
            title = driver.getTitle();
        } catch (Exception e) {
            title = "ERROR";
        }
        if (title == "ERROR") {
            switch (parBrowser) {
                case "firefox":

                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    options.addPreference(FirefoxDriver.MARIONETTE, true);
                    driver = new FirefoxDriver(options);
                    driver.manage().window().setPosition(new Point(0, 0));
                    driver.manage().window().setSize(new Dimension(1366, 768));

                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions optionsC = new ChromeOptions();
                    optionsC.addArguments(Arrays.asList(
                           "disable-infobars", "ignore-certificate-errors", "disable-popup-blocking", "disable-notifications", "no-sandbox"));
                    driver = new ChromeDriver(optionsC);
                    driver.manage().window().setSize(new Dimension(1920, 1080));
                    driver.manage().window().setPosition(new Point(0, 0));
                default:
                    break;
            }
        }
    }

    private String getAttributeType(String... parType) {
        String type;
        if (parType.length == 0) {
            type = "id";
        } else {
            type = parType[0];
        }
        return type;
    }

    public void windowPosition() {
        System.out.println("Window position X coordinates Is -> " + driver.manage().window().getPosition().getX());
        System.out.println("Window position Y coordinates Is -> " + driver.manage().window().getPosition().getY());
    }

    private WebElement findElem(String parValue, String... parType) {
        String param2 = getAttributeType(parType);
        WebElement element = null;

        try {
            switch (param2) {
                case "id":
                    element = driver.findElement(By.id(parValue));
                    break;
                case "name":
                    element = driver.findElement(By.name(parValue));
                    break;
                case "css":
                    element = driver.findElement(By.cssSelector(parValue));
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(parValue));
                    break;
                case "link":
                    element = driver.findElement(By.linkText(parValue));
                    break;
                case "partialLink":
                    element = driver.findElement(By.partialLinkText(parValue));
                case "class":
                    element = driver.findElement(By.className(parValue));
            }
        } catch (NoSuchElementException e) {
            element = null;
        }
        return element;
    }

    private List<WebElement> findElements(String parValue, String... parType) {
        String param2 = getAttributeType(parType);
        List<WebElement> element = null;

        try {
            switch (param2) {
                case "id":
                    element = driver.findElements(By.id(parValue));
                    break;
                case "name":
                    element = driver.findElements(By.name(parValue));
                    break;
                case "css":
                    element = driver.findElements(By.cssSelector(parValue));
                    break;
                case "xpath":
                    element = driver.findElements(By.xpath(parValue));
                    break;
                case "link":
                    element = driver.findElements(By.linkText(parValue));
                    break;
                case "partialLink":
                    element = driver.findElements(By.partialLinkText(parValue));
                case "class":
                    element = driver.findElements(By.className(parValue));
            }
        } catch (NoSuchElementException e) {
            element = null;
        }
        return element;
    }

    public void click(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        element.click();
    }

    public void enter(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        element.submit();
    }

    public boolean selectected(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.isSelected();
    }

    public void clear(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        element.clear();
    }

    public void openURL(String parUrl) {
        driver.get(parUrl);
    }

    public void quit() {
        driver.quit();
    }

    public void close() {
        driver.close();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public boolean isEnabled(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.isEnabled();
    }

    public boolean isDisplayed(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.isDisplayed();
    }

    public boolean elementExist(String parCss) {
        try {
            driver.findElement(By.cssSelector(parCss));
        }catch (Exception e ){
            return false;
        }
        return true;
    }

    public void sendKeys(String parText, String parName, String... parType) {
        WebElement element = findElem(parName, parType);
        element.sendKeys(parText);
    }

    public String getText(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.getText();
    }

    public String getValue(String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.getAttribute("value");
    }

    public String getValueParam(String parValue, String campoDesejado, String... parType) {
        WebElement element = findElem(parValue, parType);
        return element.getAttribute(campoDesejado);
    }

    public String getValueElement(String campoDesejado, WebElement elemento) {
        return elemento.getAttribute(campoDesejado);
    }

    public void clickByAttributeCss(String listElements, String attribute, String attributeValue) {
        List<WebElement> elements = driver.findElements(By.cssSelector(listElements));
        for (WebElement element : elements) {
            if (element.getAttribute(attributeValue).equalsIgnoreCase(attribute)) {
                element.click();
                break;
            }
        }
    }

    public void selectByIndex(Integer parIndex, String parName, String... parType) {
        WebElement element = findElem(parName, parType);
        Select dropdown = new Select(element);
        dropdown.selectByIndex(parIndex);
    }

    public void selectByText(String parText, String parName, String... parType) {
        WebElement element = findElem(parName, parType);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(parText);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void waitSeconds(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void waitElementAll(String parName, String... parType) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        String param2 = getAttributeType(parType);
        try {
            switch (param2) {
                case "id":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(parName)));
                    break;
                case "name":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(parName)));
                    break;
                case "css":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parName)));
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parName)));
                    break;
                case "link":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(parName)));
                    break;

                case "class":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(parName)));
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("ERROR WAIT => " + e.toString());
        }
    }

    public void waitElement(String parId) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(parId)));
    }

    public void waitElementMotherId(String parId) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(parId)));
    }

    public void waitElementCSS(String parCss) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parCss)));
        driver.getWindowHandles();
    }

    public void waitElementXP(String parXp) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parXp)));
    }

    public void waitElementTobeClickable(String parId) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(parId)));
    }

    public void waitElementCSSTobeClickable(String parCss) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parCss)));
    }

    public void waitElementClassTobeClickable(String parClass) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(parClass)));
    }

    public void waitElementXPTobeClickable(String parXp) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(parXp)));
    }

    public void waitElementNotVisible(String parId) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(parId)));
    }

    public void waitElementNotVisibleCSS(String parCss) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(parCss)));
        driver.getWindowHandles();
    }

    public void waitElementNotVisibleXP(String parXp) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(parXp)));
    }

    public void switchTo(String... parValue) {
        if (parValue.length == 0) {
            driver.switchTo().defaultContent();
        } else {
            driver.switchTo().window(String.valueOf(parValue));
        }
    }

    public void switchToFrameId(String parId) {
        driver.switchTo().frame(driver.findElement(By.id(parId)));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void ChooseOkOnNextConfirmation() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void ChooseCancelOnNextConfirmation() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void sendKeysTab(String texto, String parValue, String... parType) {
        WebElement element = findElem(parValue, parType);
        element.sendKeys(texto, Keys.TAB);
    }

}
