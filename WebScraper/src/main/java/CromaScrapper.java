import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CromaScrapper {

    private final String url;

    public CromaScrapper(String url) {
        this.url = url;
    }

    public Product cromaScrapperWithDriver(){
        System.out.println("OPENING CHROME DRIVER FOR CROMA PRODUCT");

        WebDriver driver = Utilities.getChromeDriverInstance();

        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info-wrap")));

        WebElement dynamicElement = driver.findElement(By.cssSelector(".info-wrap"));

        WebElement titleElement = dynamicElement.findElement(By.cssSelector(".pd-title.pd-title-normal"));
        Map<String, Object> mobileInfo = parseMobileInfo(titleElement.getText());

        WebElement priceElement = dynamicElement.findElement(By.cssSelector("#pdp-product-price"));
        double price = getPrice(priceElement.getText());

        WebElement keyFeatureElement = dynamicElement.findElement(By.cssSelector(".key-features-box"));
        WebElement displayElement = keyFeatureElement.findElement(By.cssSelector("ul li:nth-child(1)"));
        WebElement processorElement = keyFeatureElement.findElement(By.cssSelector("ul li:nth-child(3)"));
        WebElement cameraElement = keyFeatureElement.findElement(By.cssSelector("ul li:nth-child(4)"));
        Map<String, Object> cameraElementMap = processCameraElement(cameraElement.getText());

        WebElement batteryElement = keyFeatureElement.findElement(By.cssSelector("ul li:nth-child(5)"));

        Product product = Product.newBuilder()
                .website("Croma")
                .productName((String) mobileInfo.get("productName"))
                .price(price)
                .ram((Integer) mobileInfo.get("ram"))
                .storageCapacity((Integer) mobileInfo.get("storage"))
                .color((String) mobileInfo.get("color"))
                .display(processDisplayElement(displayElement.getText()))
                .battery(processBatteryElement(batteryElement.getText()))
                .frontCamera((String) cameraElementMap.get("frontCamera"))
                .rearCamera((String) cameraElementMap.get("rearCamera"))
                .processor(processProcessorElement(processorElement.getText()))
                .build();

        driver.quit();
        return product;
    }
    private static Map<String, Object> processCameraElement(String cameraElementString){
        Map<String, Object> cameraElementMap = new HashMap<>();
        int index = cameraElementString.indexOf("&");

        String rearCameraPart = cameraElementString.substring(cameraElementString.indexOf(":") + 1, index).trim();
        String frontCameraPart = cameraElementString.substring(index + 1).trim();

        cameraElementMap.put("rearCamera", extractNumericPart(rearCameraPart));
        cameraElementMap.put("frontCamera", extractNumericPart(frontCameraPart));

        return cameraElementMap;
    }

    private static double getPrice(String priceString){
        String cleanedString = priceString.replace(",", "");
        return  Double.parseDouble(cleanedString.substring(1));
    }

    private static String extractNumericPart(String part) {
        int mpIndex = part.lastIndexOf("MP");
        return part.substring(0, mpIndex + 2).trim();
    }

    private static String processDisplayElement(String processDisplayElement){
        return processDisplayElement.substring(processDisplayElement.indexOf(":") + 1).replace(",", "").trim();
    }

    private static String processBatteryElement(String batteryElementString) {
        return batteryElementString.substring(batteryElementString.indexOf(":") + 1, batteryElementString.indexOf("h") + 1).trim();
    }

    private static String processProcessorElement(String processorElement) {
        return processorElement.substring(processorElement.indexOf(":") + 1).replace(",", "").trim();
    }

    private static int processRamAndStorage(String ram){
        String processedRam = ram.substring(0, ram.indexOf("G")).trim();
        return Integer.parseInt(processedRam);
    }

    private static Map<String, Object> parseMobileInfo(String productString) {

        Map<String, Object> processedMobileInfo = new HashMap<>();

        String productName = productString.substring(0, productString.indexOf("(")).trim();
        processedMobileInfo.put("productName", productName);
        String details = productString.substring(productString.indexOf("(") + 1, productString.indexOf(")")).trim();

        String[] detailsArray = details.split(",");

        String ramString = detailsArray[0].trim();
        int ram = processRamAndStorage(ramString);
        processedMobileInfo.put("ram", ram);

        String storageString = detailsArray[1].trim();
        int storage = processRamAndStorage(storageString);
        processedMobileInfo.put("storage", storage);

        String color = detailsArray[2].trim();
        processedMobileInfo.put("color", color);

        return processedMobileInfo;

    }
}
