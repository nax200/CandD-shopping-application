package ku.cs.controllers.seller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.ThemeController;
import ku.cs.controllers.admin.AdminStatusListController;
import ku.cs.models.shop.Promotion;
import ku.cs.models.shop.PromotionList;
import ku.cs.models.user.LoginCustomer;
import ku.cs.services.ConditionFilterer;
import ku.cs.services.DataSource;
import ku.cs.services.PromotionFileDataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AllPromotionCreateController implements Initializable {

    @FXML private Circle imageProfileTitle;
    @FXML private Label usernameLabel;
    @FXML private AnchorPane parent;
    @FXML private VBox allPromotionVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(LoginCustomer.customer.getImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imageProfileTitle.setFill(new ImagePattern(image));
        usernameLabel.setText(LoginCustomer.customer.getUsername());
        DataSource<PromotionList> dataSource;
        dataSource = new PromotionFileDataSource();
        PromotionList promotionList = dataSource.readData();
        ConditionFilterer<Promotion> filterer = new ConditionFilterer<Promotion>() {
            @Override
            public boolean match(Promotion promotion) {
                return promotion.isShopName(LoginCustomer.customer.getUsername());
            }
        };
        ArrayList<Promotion> promotions = promotionList.filter(filterer);
        for(int i = 0 ;i < promotions.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/sellerpage/promotion-list.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                PromotionListController promotionListController = fxmlLoader.getController();
                promotionListController.setData(promotions.get(i));
                allPromotionVBox.getChildren().add(anchorPane);
            }
            catch (IOException e){
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
    public void handleShippedOrderButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("shipped-order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shipped-order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleStockTotalButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("stock-total");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า stock-total ไม่ได้");
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
    void goToEditProfile(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("user-profile-edit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user-profile-edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void clickLogoBackToMarketPlace(MouseEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void goToOpenShop(ActionEvent event){
        try {
            com.github.saacsos.FXRouter.goTo("market-place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market-place ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleCreatePromotionButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("create-promotion");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create-promotion ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
