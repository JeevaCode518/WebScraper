import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Utilities {

    public static WebDriver getChromeDriverInstance(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    public static void printProductReport(List<Product> productList){
        Product product1 = productList.get(0);
        Product product2 = productList.get(1);

        System.out.printf("%-20s %-50s %-50s\n", "Feature", "Product 1:  (" + product1.getWebsite() +  ")", "Product 2:  (" + product2.getWebsite() + ")");
        System.out.printf("%-20s %-50s %-50s\n", "Name", product1.getProductName(), product2.getProductName());
        System.out.printf("%-20s %-50.2f %-50.2f\n", "Price", product1.getPrice(), product2.getPrice());
        System.out.printf("%-20s %-50d %-50d\n", "RAM (GB)", product1.getRam(), product2.getRam());
        System.out.printf("%-20s %-50d %-50d\n", "Storage (GB)", product1.getStorageCapacity(), product2.getStorageCapacity());
        System.out.printf("%-20s %-50s %-50s\n", "Color", product1.getColor(), product2.getColor());
        System.out.printf("%-20s %-50s %-50s\n", "Display", product1.getDisplay(), product2.getDisplay());
        System.out.printf("%-20s %-50s %-50s\n", "Front Camera", product1.getFrontCamera(), product2.getFrontCamera());
        System.out.printf("%-20s %-50s %-50s\n", "Rear Camera", product1.getRearCamera(), product2.getRearCamera());
        System.out.printf("%-20s %-50s %-50s\n", "Battery", product1.getBattery(), product2.getBattery());
        System.out.printf("%-20s %-50s %-50s\n", "Processor", product1.getProcessor(), product2.getProcessor());
    }

    public static void generateCsvReport(List<Product> productList){
        //Update this file path with your own path to store data.
        String filePath = "/Users/jeeva/Desktop/Product_Report/sampleData.csv";
        ProductCSVWriter.writeProductsToCSV(productList, filePath);
    }

}
