package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.sellercontact.NewOrderlist;
import ku.cs.models.sellercontact.NewStokTotal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewOrderlistProduct implements Initializable {

    @FXML
    private VBox contactsLayout;


    @FXML
    public void handleStockTotalButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewSellerStochTotal");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewSellerStochTotal ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleStockFewButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewSellerStochFew");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewSellerStochFew ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleShippedButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewShipped");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewShipped ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewAddItem");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewAddItem ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewEditShop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewEditShop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<NewOrderlist> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/newOrderlistProductList.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                NewOrderlistProductList nopl = fxmlLoader.getController();
                nopl.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private List<NewOrderlist> prototype() {
        List<NewOrderlist> ls = new ArrayList<>();
        NewOrderlist prototype = new NewOrderlist();

        prototype.setId_Product("P2109180001");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        prototype.setPriceSum("199");
        prototype.setQuantity("3");
        prototype.setNameProduct("เสื้อแฟชั่น");
        prototype.setTrackingNumber("ED 1234 5678 9 TH");
        ls.add(prototype);

        prototype = new NewOrderlist();
        prototype.setId_Product("P2109180002");
        prototype.setImgSrc("/images/marketpage/img_6.png");
        prototype.setPriceSum("259");
        prototype.setQuantity("4");
        prototype.setNameProduct("รองเท้าแฟชั่น");
        prototype.setTrackingNumber("ED 1234 5678 8 TH");
        ls.add(prototype);
        return ls;
    }
}//end
