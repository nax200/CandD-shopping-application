package ku.cs.controllers.seller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.Promotion;
import ku.cs.models.shop.PromotionBaht;
import ku.cs.models.shop.PromotionPercent;

import java.net.URL;
import java.util.ResourceBundle;

public class PromotionListController implements Initializable {
    @FXML
    private AnchorPane parent;

    @FXML
    private Label nameCodeLabel;

    @FXML
    private Label idCodeLabel;

    @FXML
    private Label typePromotionLabel;

    @FXML
    private Label minimumAmountLabel;

    @FXML
    private Label discountLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }
    public void setData(Promotion promotion){
        nameCodeLabel.setText(promotion.getPromotionName());
        idCodeLabel.setText(promotion.getPromotionCode());
        if (promotion instanceof PromotionBaht){
            typePromotionLabel.setText("ลดเป็นจำนวนบาท");
            minimumAmountLabel.setText("จำนวนขั้นต่ำ "+((PromotionBaht) promotion).getMinimumAmount()+" ชิ้น");
            discountLabel.setText(((PromotionBaht) promotion).getBaht()+" บาท");
        }
        else if(promotion instanceof PromotionPercent){
            typePromotionLabel.setText("ลดเป็นเปอร์เซ็นต์");
            minimumAmountLabel.setText("ราคาขั้นต่ำ "+((PromotionPercent) promotion).getMinimumPurchase()+" บาท");
            discountLabel.setText(((PromotionPercent) promotion).getPercent()+" %");
        }
    }
}
