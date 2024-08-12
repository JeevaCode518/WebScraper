import java.util.List;

public class FlipkartScraperTask implements Runnable{

    private final String url;
    private final List<Product> listOfProduct;
    public FlipkartScraperTask(String url, List<Product> listOfProduct) {
        this.url = url;
        this.listOfProduct = listOfProduct;
    }

    @Override
    public void run() {

        FlipkartScraper flipkartScrper = new FlipkartScraper(url);
        Product product = flipkartScrper.flipKartScraperWithDriver();
        listOfProduct.add(product);
    }
}
