package ku.cs.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import ku.cs.models.user.Admin;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import com.github.saacsos.FXRouter;
public class AdminChangePassword {
    @FXML
    private TextField userNameTextField ;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;

    private UserList userList;
    private User admin;
    DataSource<UserList> dataSource;

    @FXML
    public void initialize(){
        dataSource = new UserFileDataSource();
        this.userList = dataSource.readData();
        admin = (User) com.github.saacsos.FXRouter.getData();
        admin = ((User) userList.searchUsername(admin.getUsername()));
        userNameTextField.setText(admin.getUsername());
    }
    @FXML
    void cancelButton() {
        oldPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
    }
    @FXML
    void goToAdminUserView(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin-user-view");
        }catch (IOException e){
            System.err.println("ไปหน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบ route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void saveButton() {
        if(!oldPasswordField.getText().equals("") && !newPasswordField.getText().equals("")&&!confirmPasswordField.getText().equals("")){
            if(oldPasswordField.getText().equals(admin.getPassword())){
                if(newPasswordField.getText().equals(confirmPasswordField.getText())){
                    admin.setPassword(newPasswordField.getText());
                    dataSource.writeData(userList);
                    try {
                        com.github.saacsos.FXRouter.goTo("admin-user-view");
                    } catch (IOException e) {
                        System.err.println("ไปหน้า userList ไม่ได้");
                        System.err.println("ให้ตรวจสอบ route");
                    }
                }
                else {
                    System.out.println("รหัสผ่านไม่ตรงกัน");
                    messageLabel.setText("รหัสผ่านไม่ตรงกัน");
                }
            }
            else {
                System.out.println("รหัสผ่านเดิมไม่ถูกต้อง");
                messageLabel.setText("รหัสผ่านเดิมไม่ถูกต้อง");
            }
        }
        else {
            System.out.println("โปรดใส่ข้อมูลให้ครบถ้วน");
            messageLabel.setText("โปรดใส่ข้อมูลให้ครบถ้วน");
        }
    }


}
