package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ProductReport {

    @FXML
    public void handleStockButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("SellPage-stock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Stock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleProductOnTheShelfButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("ProductOnTheShelf");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ProductOnTheShelf ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("AddItem");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AddItem ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

}//end
