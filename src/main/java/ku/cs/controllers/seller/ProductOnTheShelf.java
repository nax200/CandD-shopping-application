package ku.cs.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.sellercontact.ProductOnTheShelfprototypes;
import ku.cs.models.sellercontact.Stockprototypes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductOnTheShelf implements Initializable {

    @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ProductOnTheShelfprototypes> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/ProductOnTheShelfList.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                ProductOnTheShelfList pos = fxmlLoader.getController();
                pos.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

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
    public void handleAddItemButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("AddItem");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AddItem ไม่ได้");
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

    private List<ProductOnTheShelfprototypes> prototype(){
        List<ProductOnTheShelfprototypes> ls = new ArrayList<>();
        ProductOnTheShelfprototypes prototype = new ProductOnTheShelfprototypes();

        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);

        prototype = new ProductOnTheShelfprototypes();
        prototype.setNameProduct("เสื้อผ้าคุณภาพ");
        prototype.setTreasury("100");
        prototype.setPrice("250");
        prototype.setNote("-");
        prototype.setImgSrc("/images/marketpage/img_1.png");
        ls.add(prototype);


        return ls;
    }


}//end
