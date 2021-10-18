package ku.cs.controllers.seller.promotion;

import javafx.fxml.FXML;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.promotion.PromotionBaht;
import ku.cs.models.shop.promotion.PromotionList;
import ku.cs.models.shop.promotion.PromotionPercent;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.DataSource;
import ku.cs.services.PromotionFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatePromotionController implements Initializable {
    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private AnchorPane parent;
    @FXML private TextField nameCodeTextField;
    @FXML private TextField codeIdTextField;
    @FXML private ComboBox<String> typeCodeComboBox;
    @FXML private TextField discountTextField;
    @FXML private TextField minimumAmount;
    @FXML private Label messageLabel;
    @FXML private ComboBox<String> useConditionCombobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
        typeCodeComboBox.setValue("");
        typeCodeComboBox.getItems().addAll("มูลค่าส่วนลด (บาท)", "โดย %");
        useConditionCombobox.setValue("");
        useConditionCombobox.getItems().addAll("ราคาขั้นต่ำ","จำนวนขั้นต่ำ");
    }

    @FXML
    public void handleCancelButton(){
        nameCodeTextField.clear();
        codeIdTextField.clear();
        typeCodeComboBox.setValue("");
        useConditionCombobox.setValue("");
        discountTextField.clear();
        minimumAmount.clear();
    }

    @FXML
    void handleConfirmButton(){
        DataSource<PromotionList> dataSource;
        dataSource = new PromotionFileDataSource();
        PromotionList promotions = dataSource.readData();
        if(     nameCodeTextField.getText().equals("") || codeIdTextField.getText().equals("") ||
                typeCodeComboBox.getValue().equals("") || discountTextField.getText().equals("") ||
                minimumAmount.getText().equals("") || useConditionCombobox.getValue().equals("")
        ){
            messageLabel.setText("กรุณากรอกข้อมูลให้ครบก่อนดำเนินการ");
            return;
        }
        if(promotions.searchPromotion(codeIdTextField.getText()) != null){
            messageLabel.setText("ขออภัยรหัสส่วนลดนี้ถูกใช้แล้ว");
            return;
        }
        String condition = useConditionCombobox.getValue();
        try {
            if (    Double.parseDouble(minimumAmount.getText().trim()) <= 0 ||
                    Double.parseDouble(discountTextField.getText().trim()) <= 0 )
            {
                messageLabel.setText("ข้อมูลที่กรอกไม่ถูกต้อง");
                return;
            }
            if(useConditionCombobox.getValue().trim().equals("จำนวนขั้นต่ำ")){
                int amountInt = (int) Double.parseDouble(minimumAmount.getText());
                double amountDouble = Double.parseDouble(minimumAmount.getText());
                if (amountDouble-amountInt != 0.0) {
                    messageLabel.setText("โปรดใส่จำนวนขั้นต่ำให้เป็นจำนวนเต็ม");
                    return;
                }
            }
            // สร้าง obj
            if (typeCodeComboBox.getValue().equals("มูลค่าส่วนลด (บาท)")) {
                promotions.addPromotion(new PromotionBaht(nameCodeTextField.getText().trim(),
                        codeIdTextField.getText().trim(),
                        LoginCustomer.customer,
                        condition,
                        Double.parseDouble(minimumAmount.getText().trim()),
                        Double.parseDouble(discountTextField.getText().trim())));
            }
            else if (typeCodeComboBox.getValue().equals("โดย %")) {
                if (Double.parseDouble(discountTextField.getText().trim()) > 100) {
                    messageLabel.setText("ข้อมูลที่กรอกไม่ถูกต้อง");
                    return;
                }
                promotions.addPromotion(new PromotionPercent(nameCodeTextField.getText().trim(),
                        codeIdTextField.getText().trim(),
                        LoginCustomer.customer,
                        condition,
                        Double.parseDouble(minimumAmount.getText().trim()),
                        Double.parseDouble(discountTextField.getText().trim())));
            }
        } catch (IllegalArgumentException e){
            messageLabel.setText("โปรดใส่ข้อมูลให้ถูกประเภท");
            return;
        }
        dataSource.writeData(promotions);
        try {
            com.github.saacsos.FXRouter.goTo("all-promotion-create");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า all-promotion-create ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleLowStockButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("low-stock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า low-stock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleNewOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("new-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า new-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleShippedOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("shipped-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shipped-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleStockTotalButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า stock-total ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("add-item");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add-item ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    void goToEditProfile(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
    void goToOpenShop(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleCreatePromotionButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("create-promotion");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create-promotion ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    void handleAllPromotionCreateButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("all-promotion-create");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า all-promotion-create ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
