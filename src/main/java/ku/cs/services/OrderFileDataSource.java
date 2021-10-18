package ku.cs.services;

import ku.cs.models.shop.order.Order;
import ku.cs.models.shop.order.OrderList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFileDataSource implements DataSource<OrderList> {
    private String directoryName;
    private String filename;

    public OrderFileDataSource(){
        this("csv-data/order","order-list.csv");
    }

    public OrderFileDataSource(String directoryName, String filename){
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
    public OrderList readData() {
        OrderList orderList = new OrderList();
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String line = "";
            while( (line = buffer.readLine()) != null ) {
                String[] data = line.split(",");
                String type = data[0];
                orderList.addOrder(
                        new Order(
                                LocalDateTime.parse(data[0],dtf), // addedTime
                                data[1], //orderNo
                                data[2], //buyer
                                data[3], //productID
                                Integer.parseInt(data[4]), //quantity
                                data[5],  //tracking number
                                data[6],  //address
                                data[7] // idPromotion
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

        return orderList;
    }

    @Override
    public void writeData(OrderList order) {

        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(order.toCsv());

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
