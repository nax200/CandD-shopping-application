package ku.cs.controllers.seller.promotion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.ThemeController;
import ku.cs.models.shop.promotion.Promotion;
import ku.cs.models.shop.promotion.PromotionBaht;
import ku.cs.models.shop.promotion.PromotionPercent;

import java.net.URL;
import java.util.ResourceBundle;

public class PromotionListController implements Initializable {
    @FXML private AnchorPane parent;
    @FXML private Label nameCodeLabel;
    @FXML private Label idCodeLabel;
    @FXML private Label typePromotionLabel;
    @FXML private Label minimumAmountLabel;
    @FXML private Label discountLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void setData(Promotion promotion){
        nameCodeLabel.setText(promotion.getPromotionName());
        idCodeLabel.setText(promotion.getPromotionCode());
        if(promotion.getUseCondition().equals("จำนวนขั้นต่ำ")){
            minimumAmountLabel.setText("จำนวนขั้นต่ำ " + promotion.getMinimumAmountString() + " ชิ้น");
        }
        else if (promotion.getUseCondition().equals("ราคาขั้นต่ำ")){
            minimumAmountLabel.setText("ราคาขั้นต่ำ " + promotion.getMinimumAmountString() + " บาท");
        }

        if (promotion instanceof PromotionBaht){
            typePromotionLabel.setText("ลดเป็นจำนวนบาท");
            discountLabel.setText(((PromotionBaht) promotion).getBahtString() + " บาท");
        }
        else if(promotion instanceof PromotionPercent){
            typePromotionLabel.setText("ลดเป็นเปอร์เซ็นต์");
            discountLabel.setText(((PromotionPercent) promotion).getPercentString()  + " %");
        }

    }
}
