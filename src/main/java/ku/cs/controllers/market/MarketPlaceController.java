package ku.cs.controllers.market;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketPlaceController implements Initializable{
    @FXML private ChoiceBox<String> category;
    @FXML private ChoiceBox<String> sort;
    @FXML private Label categoryLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort.getItems().addAll("ราคาน้อยไปมาก", "ราคามากไปน้อย", "ความนิยม");
        category.getItems().addAll("เครื่องแต่งกาย", "เสื้อผ้าแฟชั่น");
        category.setOnAction(actionEvent -> categoryLabel.setText(category.getValue()));
    }
}
