package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "C&D Online Shopping", 1024,768);
        configRoute();
        FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr+ "loginpage/loginpage.fxml");
        FXRouter.when("register", packageStr+ "loginpage/registerpage.fxml");
        FXRouter.when("credit", packageStr+ "loginpage/creditpage.fxml");

        FXRouter.when("market-place", packageStr+ "marketpage/market-place.fxml");
        FXRouter.when("user-profile-edit", packageStr+ "marketpage/user-profile-edit.fxml");
        FXRouter.when("open-shop", packageStr+ "marketpage/open-shop.fxml");
        FXRouter.when("product", packageStr+ "marketpage/product.fxml");
        FXRouter.when("shop", packageStr+ "marketpage/shop.fxml");
        FXRouter.when("purchase", packageStr+ "marketpage/purchase.fxml");
        FXRouter.when("order", packageStr+ "marketpage/order.fxml");

        FXRouter.when("admin-user-view", packageStr+ "adminpage/admin-user.fxml");
        FXRouter.when("admin-reported-list", packageStr+ "adminpage/admin-report.fxml");
        FXRouter.when("admin-blocked-list", packageStr+ "adminpage/admin-status.fxml");
        FXRouter.when("admin-change-password", packageStr+ "adminpage/admin-change-password.fxml");
        FXRouter.when("admin-reported-product-list", packageStr+ "adminpage/admin-report-product.fxml");

        FXRouter.when("stock-total", packageStr+ "sellerpage/stock-total.fxml");
        FXRouter.when("low-stock", packageStr+ "sellerpage/low-stock.fxml");
        FXRouter.when("new-order", packageStr+ "sellerpage/new-order.fxml");
        FXRouter.when("shipped-order", packageStr+ "sellerpage/shipped-order.fxml");
        FXRouter.when("add-item", packageStr+ "sellerpage/add-item.fxml");
        FXRouter.when("edit-shop", packageStr+ "sellerpage/edit-shop.fxml");
        FXRouter.when("edit-add-shop", packageStr+ "sellerpage/edit-add-item.fxml");

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}