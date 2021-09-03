package ku.cs.controllers.seller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.sellercontact.Contactprototypes;
import ku.cs.controllers.seller.Contactprototype;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellerPage2 implements Initializable {
    @FXML
    private VBox contactsLayout;//ไม่ขึ้นfxml

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Contactprototypes> prototype = new ArrayList<>(prototype());
        for (int i = 0; i < prototype.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/Contactprototype.fxml"));

            try {

                HBox hBox = fxmlLoader.load();
                Contactprototype cp = fxmlLoader.getController();
                cp.setData(prototype.get(i));
                contactsLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private List<Contactprototypes> prototype(){
        List<Contactprototypes> ls = new ArrayList<>();
        Contactprototypes prototype = new Contactprototypes();

        prototype.setName("mos");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("moss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mossss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype.setName("mosssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        prototype = new Contactprototypes();
        prototype.setName("mosssssss");
        prototype.setImgSrc("@../../../images/sellerpage/profile.png");
        prototype.setNameProduct("เสื้อผ้า");
        prototype.setPrice("399");
        prototype.setProductStatus("รอจัดส่ง");
        prototype.setParcelNumber("1234567890123");
        prototype.setQuantity("1");
        ls.add(prototype);

        return ls;
    }

}//end
