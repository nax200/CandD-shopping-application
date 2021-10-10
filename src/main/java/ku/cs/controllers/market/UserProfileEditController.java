package ku.cs.controllers.market;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.github.saacsos.FXRouter;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.user.Customer;
import ku.cs.models.user.LoginCustomer;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

public class UserProfileEditController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextArea addressTextArea;
    @FXML private Circle profileImage;
    @FXML private Circle profileImageTab;
    @FXML private Label usernameLabel;
    @FXML private Label messageLabel;

    private File imageFile = null;
    private UserList userList;
    private Customer loginCustomer;
    DataSource<UserList> dataSource;

    @FXML
    public void initialize() {
        dataSource = new UserFileDataSource();
        this.userList = dataSource.readData();
        loginCustomer = ( (Customer)userList.searchUsername( LoginCustomer.customer.getUsername() ) );


        nameTextField.setText(loginCustomer.getName());
        usernameTextField.setText(loginCustomer.getUsername());
        usernameLabel.setText(loginCustomer.getUsername());

        try {
            BufferedImage bufferedImage = ImageIO.read(loginCustomer.getImageFile());
            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            profileImage.setFill(new ImagePattern(image));
            profileImageTab.setFill(new ImagePattern(image));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToOpenShop(ActionEvent event){
        try {
            if(LoginCustomer.customer.getShopName().equals("-")) {
                FXRouter.goTo("open-shop");
            }
            else {
                FXRouter.goTo("stock-total");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า open-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickToMarketPlace(ActionEvent event) {
        try {
            FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void saveButton() throws IOException {
        boolean isChangePassword = true;
        if (!oldPasswordField.getText().equals("") && !newPasswordField.getText().equals("") && !confirmPasswordField.getText().equals("")) {
            if (oldPasswordField.getText().equals(loginCustomer.getPassword())){
                if ( newPasswordField.getText().equals(confirmPasswordField.getText()) ) {
                    loginCustomer.setPassword(newPasswordField.getText());
                }
                else{
                    isChangePassword = false;
                    System.out.println("รหัสผ่านไม่ตรงกัน");
                    messageLabel.setText("รหัสผ่านไม่ตรงกัน");
                }
            }
            else{
                isChangePassword = false;
                System.out.println("รหัสผ่านเดิมไม่ถูกต้อง");
                messageLabel.setText("รหัสผ่านเดิมไม่ถูกต้อง");
            }
        }
        if(isChangePassword) {
            loginCustomer.setName(nameTextField.getText());
            if (imageFile != null) {
                loginCustomer.setImageFile(imageFile);
                loginCustomer.copyImageFile();
            }

            dataSource.writeData(userList);
            FXRouter.goTo("market-place");
        }
    }

    @FXML
    void cancelButton() {
        oldPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(loginCustomer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image img = SwingFXUtils.toFXImage(bufferedImage, null);
        profileImage.setFill(new ImagePattern(img));
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    /**
     * เมื่อกดปุ่มนี้ จะเรียกใช้หน้าต่างเลือกไฟล์ ให้ผู้ใช้ได้เลือกรูปภาพ
     * เมื่อเลือกเสร็จสิ้น รูปภาพจะอัปเดตใน view
     */
    @FXML
    void browseImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("เลือกรูปภาพ...");

        // ใช้ filter เพื่อกรองเอาแต่ไฟล์ jpg และ png
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        fileChooser.setInitialDirectory(userDirectory);

        imageFile = fileChooser.showOpenDialog(null);

        if (imageFile != null)
        {
            try {
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                profileImage.setFill(new ImagePattern(img));
            } catch (Exception e) {
                System.err.println("เกิดปัญหาในการเลือกไฟล์");
            }
        }
    }
}
