package testpackedge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class AmazonPerformanceTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--disable-gpu");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to Amazon
            driver.get("https://www.amazon.com");

            // Wait for the page to load
            Thread.sleep(5000); // Simple wait, can be replaced with WebDriverWait

            // Get page title
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);

            // Get performance timing metrics
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object performanceTiming = js.executeScript("return window.performance.timing");

            // Extract performance timing metrics
            long navigationStart = (long) js.executeScript("return window.performance.timing.navigationStart");
            long domContentLoadedEventEnd = (long) js.executeScript("return window.performance.timing.domContentLoadedEventEnd");
            long loadEventEnd = (long) js.executeScript("return window.performance.timing.loadEventEnd");

            long loadTime = domContentLoadedEventEnd - navigationStart;
            long totalLoadTime = loadEventEnd - navigationStart;

            System.out.println("Page Load Time: " + loadTime / 1000.0 + " seconds");
            System.out.println("Total Load Time: " + totalLoadTime / 1000.0 + " seconds");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Clean up
            driver.quit();
        }
    }
}




