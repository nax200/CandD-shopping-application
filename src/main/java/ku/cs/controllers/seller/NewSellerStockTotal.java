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

public class NewSellerStockTotal implements Initializable {
    @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<NewStokTotal> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/newSellerStockTotalList.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                NewSellerStockTotalList nsstl = fxmlLoader.getController();
                nsstl.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
    public void handleOrderlistButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewOrderlistProduct");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewOrderlistProduct ไม่ได้");
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
        prototype.setQuantity("5");
        prototype.setNameProduct("รองเท้าแฟชั่น");
        ls.add(prototype);

        prototype = new NewStokTotal();
        prototype.setId_Product("P2109180003");
        prototype.setImgSrc("/images/marketpage/img_7.png");
        prototype.setPrice("229");
        prototype.setQuantity("30");
        prototype.setNameProduct("กระโปรงแฟชั่น");
        ls.add(prototype);

        prototype = new NewStokTotal();
        prototype.setId_Product("P2109180004");
        prototype.setImgSrc("/images/marketpage/img_8.png");
        prototype.setPrice("899");
        prototype.setQuantity("15");
        prototype.setNameProduct("กางเกงแฟชั่น");
        ls.add(prototype);
        return ls;
    }
}//end
