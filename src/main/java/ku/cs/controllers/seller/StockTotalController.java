package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.sellercontact.StockTotal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StockTotalController implements Initializable {
    @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<StockTotal> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/stock-total-list.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                StockTotalListController stockTotalList = fxmlLoader.getController();
                stockTotalList.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
    public void handleEditShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit-shop");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit-shop ไม่ได้");
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
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("add-item");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add-item ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    private List<StockTotal> prototype() {
        List<StockTotal> ls = new ArrayList<>();
        StockTotal prototype = new StockTotal();

        prototype.setId_Product("P2109180001");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        prototype.setPrice("199");
        prototype.setQuantity("3");
        prototype.setNameProduct("เสื้อแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180002");
        prototype.setImgSrc("/images/marketpage/img_6.png");
        prototype.setPrice("259");
        prototype.setQuantity("5");
        prototype.setNameProduct("รองเท้าแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180003");
        prototype.setImgSrc("/images/marketpage/img_7.png");
        prototype.setPrice("229");
        prototype.setQuantity("30");
        prototype.setNameProduct("กระโปรงแฟชั่น");
        ls.add(prototype);

        prototype = new StockTotal();
        prototype.setId_Product("P2109180004");
        prototype.setImgSrc("/images/marketpage/img_8.png");
        prototype.setPrice("899");
        prototype.setQuantity("15");
        prototype.setNameProduct("กางเกงแฟชั่น");
        ls.add(prototype);
        return ls;
    }
}//end
