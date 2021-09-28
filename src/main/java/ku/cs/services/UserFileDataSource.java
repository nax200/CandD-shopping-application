package ku.cs.services;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import ku.cs.models.user.Admin;
import ku.cs.models.user.Customer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFileDataSource implements DataSource<UserList>{
    private String directoryName;
    private String filename;

    public UserFileDataSource() {
        this("csv-data/user","user-account.csv");
    }

    public UserFileDataSource(String directoryName, String filename) {
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
    public UserList readData() {
        UserList userList = new UserList();

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
                if (type.equals("User")) {
                    userList.addUser(
                            new User(
                                    data[1], // username
                                    data[2], // password
                                    LocalDateTime.parse(data[3],dtf))  // lastTimeLoggedIn
                            );

                } else if (type.equals("Customer")) {
                    userList.addUser(
                            new Customer(
                                    data[1], // userID
                                    data[2], // username
                                    data[3], // password
                                    LocalDateTime.parse(data[4],dtf), // lastTimeLoggedIn
                                    data[5], // name
                                    Boolean.parseBoolean(data[6]), // isBlocked
                                    data[7], // shopName
                                    data[8] // imageFilePath
                            )
                    );
                }
                else if (type.equals("Admin")) {
                    userList.addUser(
                            new Admin(
                                    data[1], // username
                                    data[2], // password
                                    LocalDateTime.parse(data[3],dtf)  // lastTimeLoggedIn
                            )
                    );
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

        return userList;
    }

    @Override
    public void writeData(UserList userList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(userList.toCsv());

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
