import java.util.List;

public class CromaScraperTask implements Runnable{

    private final String url;
    private final List<Product> listOfProduct;
    public CromaScraperTask(String url, List<Product> listOfProduct) {
        this.url = url;
        this.listOfProduct = listOfProduct;
    }

    @Override
    public void run() {

        CromaScrapper cromaScrapper = new CromaScrapper(url);
        Product product = cromaScrapper.cromaScrapperWithDriver();
        listOfProduct.add(product);
    }
}
