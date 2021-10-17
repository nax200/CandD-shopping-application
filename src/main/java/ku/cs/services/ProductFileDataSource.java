package ku.cs.services;

import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.product.ProductList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductFileDataSource implements DataSource<ProductList> {
    private String directoryName;
    private String filename;

    public ProductFileDataSource() {
        this("csv-data/product","product-list.csv");
    }

    public ProductFileDataSource(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }

    private void initialFileIfNotExist() {
        File file = new File(directoryName);
        if (!file.exists()){
            file.mkdir();
        }

        String path = directoryName + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public ProductList readData() {
        ProductList productList = new ProductList();

        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);


            String line = "";
            while( (line = buffer.readLine()) != null ) {
                String[] data = line.split(",");
                String type = data[0];
                productList.addProduct(
                        new Product(
                                LocalDateTime.parse(data[0],dtf), // addedTime
                                data[1], // ID
                                data[2], // shopName
                                data[3], // name
                                Double.parseDouble(data[4]), // price
                                Integer.parseInt(data[5]), // remaining
                                Double.parseDouble(data[6]), // rating
                                data[7], // imageFilePath
                                data[8],  // detail
                                Integer.parseInt(data[9]), // numRemainWarning
                                data[10] // type
                        )
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productList;
    }

    @Override
    public void writeData(ProductList productList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(productList.toCsv());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
