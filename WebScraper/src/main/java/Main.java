import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Please edit this property with your chromeDriver path;
        System.setProperty("webdriver.chrome.driver", "/Users/jeeva/Downloads/chromedriver-mac-x64/chromedriver");

        //Edit this two URL to check Other combinations (croma, croma / flipkart, flipkart)
        String url1 = "https://www.croma.com/oppo-a3-pro-5g-8gb-ram-256gb-moonlit-purple-/p/307492";
        String url2 = "https://www.flipkart.com/oppo-k12x-5g-45w-supervooc-charger-in-the-box-breeze-blue-128-gb/p/itma30cb38861d4c?pid=MOBH2Q4PN6FWZCGV";

        try {
            List<Product> productList = new ArrayList<>();
            Runnable scraperTask1 = identifyWebsite(url1, productList);
            Runnable scraperTask2 = identifyWebsite(url2, productList);

            Thread thread1 = new Thread(scraperTask1);
            Thread thread2 = new Thread(scraperTask2);

            System.out.println("Scraping started!");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("Scraping completed!");
            System.out.println("Generating Report");

            Utilities.generateCsvReport(productList);
            Utilities.printProductReport(productList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Runnable identifyWebsite(String url, List<Product> productList) {
        Runnable runnableTask = null;

        if (url.contains("flipkart.com")) {
            runnableTask = new FlipkartScraperTask(url, productList);
        } else if (url.contains("croma.com")) {
            runnableTask =  new CromaScraperTask(url, productList);
        }
        return runnableTask;
    }

}
