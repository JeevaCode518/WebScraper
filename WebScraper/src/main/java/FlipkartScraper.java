import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

public class FlipkartScraper {

    private final String url;

    public FlipkartScraper(String url) {
        this.url = url;
    }

    public Product flipKartScraperWithDriver(){
        System.out.println("OPENING CHROME DRIVER FOR FLIPKART PRODUCT");
        WebDriver driver = Utilities.getChromeDriverInstance();
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".DOjaWF")));

        WebElement dynamicElement = driver.findElement(By.cssSelector(".DOjaWF"));

        WebElement titleElement = dynamicElement.findElement(By.cssSelector(".VU-ZEz"));
        Map<String, Object> mobileInfo = parseMobileInfo(titleElement.getText());

        WebElement priceElement = dynamicElement.findElement(By.cssSelector(".Nx9bqj"));
        WebElement highlightElement = dynamicElement.findElement(By.cssSelector(".xFVion"));
        WebElement displayElement = highlightElement.findElement(By.cssSelector("ul li:nth-child(2)"));
        WebElement cameraElement = highlightElement.findElement(By.cssSelector("ul li:nth-child(3)"));
        WebElement batteryElement = highlightElement.findElement(By.cssSelector("ul li:nth-child(4)"));
        WebElement processorElement = highlightElement.findElement(By.cssSelector("ul li:nth-child(5)"));


        Map<String, Object> cameraElementMap = processCameraElement(cameraElement.getText());
        double price = processPriceElement(priceElement.getText());

        Product product = Product.newBuilder()
                .website("FlipKart")
                .productName((String) mobileInfo.get("productName"))
                .price(price)
                .ram((Integer) mobileInfo.get("ram"))
                .storageCapacity((Integer) mobileInfo.get("storage"))
                .color((String) mobileInfo.get("color"))
                .display(displayElement.getText())
                .frontCamera((String) cameraElementMap.get("frontCamera"))
                .rearCamera((String) cameraElementMap.get("rearCamera"))
                .battery(processBatteryElement(batteryElement.getText()))
                .processor(processorElement.getText())
                .build();

        driver.quit();
        return product;
    }

    private static Map<String, Object> processCameraElement(String cameraElement){
        Map<String, Object> cameraElementMap = new HashMap<>();
        String[] tempArray = cameraElement.split("\\|");
        String frontCamera = tempArray[1].substring(0,tempArray[1].indexOf("F")).trim();
        cameraElementMap.put("rearCamera", tempArray[0].trim());
        cameraElementMap.put("frontCamera", frontCamera);
        return cameraElementMap;
    }

    private static String processBatteryElement(String batteryElement){
        return batteryElement.substring(0, batteryElement.indexOf("B")).trim();
    }

     private static double processPriceElement(String prices){

        String cleanedPrice = prices.substring(1).replace(",", "");
        return Double.parseDouble(cleanedPrice);
    }

    private static int processRamAndStorage(String ram){
        String processedRam = ram.substring(0, ram.indexOf("G")).trim();
        return Integer.parseInt(processedRam);
    }

    private static Map<String, Object> parseMobileInfo(String productString) {
        Map<String, Object> proceesedMobileInfo = new HashMap<>();
        String productName = productString.substring(0, productString.indexOf("(")).trim();
        proceesedMobileInfo.put("productName", productName);

        String ramString = productString.substring(productString.lastIndexOf("(") + 1, productString.lastIndexOf(")")).trim();
        int ram = processRamAndStorage(ramString);
        proceesedMobileInfo.put("ram", ram);

        String firstDetails = productString.substring(productString.indexOf("(") + 1, productString.indexOf(")")).trim();
        String[] firstDetailsArray = firstDetails.split(",");
        String color = firstDetailsArray[0].trim();
        String storageString = firstDetailsArray[1].trim();

        int storage = processRamAndStorage(storageString);
        proceesedMobileInfo.put("color", color);
        proceesedMobileInfo.put("storage", storage);

        return proceesedMobileInfo;
    }

}
