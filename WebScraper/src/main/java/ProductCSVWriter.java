import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCSVWriter {

    public static void writeProductsToCSV(List<Product> products, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("Product Name,Price,RAM,Storage,Color,Display,Front Camera,Rear Camera,Battery,Processor");
            writer.newLine();

            for (Product product : products) {
                writer.write(product.getProductName() + "," +
                        product.getPrice() + "," +
                        product.getRam() + "," +
                        product.getStorageCapacity() + "," +
                        product.getColor() + "," +
                        product.getDisplay() + "," +
                        product.getFrontCamera() + "," +
                        product.getRearCamera() + "," +
                        product.getBattery() + "," +
                        product.getProcessor());
                writer.newLine();
            }
            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
