package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddItem {


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
            System.err.println("ไปที่หน้า Stock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleProductReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("ProductReport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ProductReport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleMarketButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("marketPlace");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketPlace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }


}//end
