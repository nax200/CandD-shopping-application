package ku.cs.services;

import ku.cs.models.admin.Report;
import ku.cs.models.admin.ReportList;
import ku.cs.models.admin.ReportedComment;
import ku.cs.models.admin.ReportedProduct;
import ku.cs.models.shop.Comment;
import ku.cs.models.shop.CommentList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportFileDataSource implements DataSource<ReportList> {
    private String directoryName;
    private String filename;

    public ReportFileDataSource(){this("csv-data/report","report-list.csv");}

    public ReportFileDataSource(String directoryName, String filename) {
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
    public ReportList readData() {
        ReportList reportList = new ReportList();

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
                if (data[0].equals("ReportedComment"))
                    reportList.addReport(
                            new ReportedComment(
                                    data[1], //reportId
                                    data[2], //reportedUsername
                                    data[3], //reporterUsername
                                    data[4], //reportType
                                    data[5],//detail
                                    LocalDateTime.parse(data[6], dtf),// timeToReport
                                    Boolean.parseBoolean(data[7]),//isChecked
                                    Boolean.parseBoolean(data[8]),//recentCase
                                    data[9]// idComment
                            ));

                else if (data[0].equals("ReportedProduct")) {
                    reportList.addReport(new ReportedProduct( data[1], //reportId
                            data[2], //reportedUsername
                            data[3], //reporterUsername
                            data[4], //reportType
                            data[5],//detail
                            LocalDateTime.parse(data[6], dtf), // timeToReport
                            Boolean.parseBoolean(data[7]),//isChecked
                            Boolean.parseBoolean(data[8]),//recentCase
                            data[8] //productId
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

        return reportList;
    }

    @Override
    public void writeData(ReportList reportList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(reportList.toCsv());

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
