package ku.cs.services;

import ku.cs.models.shop.promotion.PromotionBaht;
import ku.cs.models.shop.promotion.PromotionList;
import ku.cs.models.shop.promotion.PromotionPercent;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PromotionFileDataSource implements DataSource<PromotionList>{
    private String directoryName;
    private String filename;

    public PromotionFileDataSource(){
        this("csv-data/product","promotion-list.csv");
    }

    public PromotionFileDataSource(String directoryName, String filename) {
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
    public PromotionList readData() {
        PromotionList promotionList = new PromotionList();

        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file,StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);


            String line = "";
            while( (line = buffer.readLine()) != null ) {
                String[] data = line.split(",");
                if (data[0].equals("PromotionBaht"))
                    promotionList.addPromotion(
                           new PromotionBaht(
                                    data[1], //reportId
                                    data[2], //reportedUsername
                                    data[3], //promotionShopName
                                    data[4], //useCondition
                                    Double.parseDouble(data[5]), //minimumAmount
                                    Double.parseDouble(data[6])// baht
                            ));

                else if (data[0].equals("PromotionPercent")) {
                    promotionList.addPromotion(new PromotionPercent(
                            data[1], //promotionName
                            data[2], //promotionCode
                            data[3], //promotionShopName
                            data[4], //useCondition
                            Double.parseDouble(data[5]), //minimumAmount
                            Double.parseDouble(data[6])//Percent

                    ));
                }
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

        return promotionList;
    }

    @Override
    public void writeData(PromotionList promotionList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            buffer.write(promotionList.toCsv());

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
