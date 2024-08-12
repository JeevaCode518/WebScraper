

public class Product {

    private String website;
    private String productName;
    private double price;
    private Integer ram;
    private Integer storageCapacity;
    private String color;
    private String display;
    private String frontCamera;
    private String rearCamera;
    private String battery;
    private String processor;


    //    public Product(String productName, double price, int ram, int storageCapacity, String color) {
//        this.productName = productName;
//        this.price = price;
//        this.ram = ram;
//        this.storageCapacity = storageCapacity;
//        this.color = color;
//    }
    public Product(){}

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getStorageCapacity() {
        return storageCapacity;
    }

    public String getColor() {
        return color;
    }

    public String getWebsite() {
        return website;
    }

    public String getDisplay() {
        return display;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public String getBattery() {
        return battery;
    }

    public String getProcessor() {
        return processor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "website='" + website + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", ram=" + ram +
                ", storageCapacity=" + storageCapacity +
                ", color='" + color + '\'' +
                ", display='" + display + '\'' +
                ", frontCamera='" + frontCamera + '\'' +
                ", rearCamera='" + rearCamera + '\'' +
                ", battery='" + battery + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final Product product;

        public Builder() {
            product = new Product();
        }
        public Builder website(String website) {
            product.website = website;
            return this;
        }

        public Builder productName(String productName) {
            product.productName = productName;
            return this;
        }
        public Builder price(double price) {
            product.price = price;
            return this;
        }
        public Builder ram(int ram) {
            product.ram = ram;
            return this;
        }
        public Builder storageCapacity(int storageCapacity) {
            product.storageCapacity = storageCapacity;
            return this;
        }
        public Builder color(String color) {
            product.color = color;
            return this;
        }
        public Builder display(String display) {
            product.display = display;
            return this;
        }
        public Builder rearCamera(String rearCamera) {
            product.rearCamera = rearCamera;
            return this;
        }
        public Builder frontCamera(String frontCamera) {
            product.frontCamera = frontCamera;
            return this;
        }
        public Builder battery(String battery) {
            product.battery = battery;
            return this;
        }
        public Builder processor(String processor) {
            product.processor = processor;
            return this;
        }
        public Product build() {
            return product;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "product=" + product +
                    '}';
        }
    }
}
