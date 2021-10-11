package ku.cs.services;

import ku.cs.models.shop.Comment;
import ku.cs.models.shop.CommentList;
import ku.cs.models.shop.Product;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentFileDataSource implements DataSource<CommentList>{
    private String directoryName;
    private String filename;

    public CommentFileDataSource(){this("csv-data/comment","comment-list.csv");}

    public CommentFileDataSource(String directoryName, String filename){
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
    public CommentList readData() {
        CommentList commentList = new CommentList();

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
                commentList.addComment(
                        new Comment(
                                data[0], //idComment
                                data[1], //idProduct
                                data[2], //nameToComment
                                data[3], //comment
                                LocalDateTime.parse(data[4],dtf), // timeToComment
                                Integer.parseInt(data[5]) // rating
                        ));
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

        return commentList;
    }


    @Override
    public void writeData(CommentList commentList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(commentList.toCsv());

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
