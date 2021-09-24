package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.sellercontact.NewStokTotal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewSellerStockFew implements Initializable {

    @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<NewStokTotal> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/newSellerStockFewList.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                NewSellerStockFewList nssl = fxmlLoader.getController();
                nssl.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

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

    @FXML
    public void handleOrderlistButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewOrderlistProduct");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewOrderlistProduct ไม่ได้");
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

    private List<NewStokTotal> prototype() {
        List<NewStokTotal> ls = new ArrayList<>();
        NewStokTotal prototype = new NewStokTotal();

        prototype.setId_Product("P2109180001");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        prototype.setPrice("199");
        prototype.setQuantity("3");
        prototype.setNameProduct("เสื้อแฟชั่น");
        ls.add(prototype);

        prototype = new NewStokTotal();
        prototype.setId_Product("P2109180002");
        prototype.setImgSrc("/images/marketpage/img_6.png");
        prototype.setPrice("259");
        prototype.setQuantity("4");
        prototype.setNameProduct("รองเท้าแฟชั่น");
        ls.add(prototype);
        return ls;
    }
}//end
