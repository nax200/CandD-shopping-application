package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class NewAddItem {
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
    public void handleShippedButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("NewShipped");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า NewShipped ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
}
