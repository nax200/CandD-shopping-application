package ku.cs;

import javafx.application.Application;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class App extends Application {

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

        FXRouter.when("market-place", packageStr+ "marketpage/marketplace/market-place.fxml");
        FXRouter.when("user-profile-edit", packageStr+ "marketpage/user-profile-edit.fxml");
        FXRouter.when("open-shop", packageStr+ "marketpage/open-shop.fxml");
        FXRouter.when("product", packageStr+ "marketpage/productdetail/product.fxml");
        FXRouter.when("shop", packageStr+ "marketpage/marketplace/shop.fxml");
        FXRouter.when("purchase", packageStr+ "marketpage/purchase/purchase.fxml");
        FXRouter.when("order", packageStr+ "marketpage/purchase/order.fxml");

        FXRouter.when("admin-user-view", packageStr+ "adminpage/customerlist/admin-user.fxml");
        FXRouter.when("admin-blocked-list", packageStr+ "adminpage/blocklist/admin-status.fxml");
        FXRouter.when("admin-reported-list", packageStr+ "adminpage/reportlist/customer/admin-report.fxml");
        FXRouter.when("admin-reported-product-list", packageStr+ "adminpage/reportlist/product/admin-report-product.fxml");
        FXRouter.when("admin-change-password", packageStr+ "adminpage/admin-change-password.fxml");

        FXRouter.when("stock-total", packageStr+ "sellerpage/stock/stock-total.fxml");
        FXRouter.when("low-stock", packageStr+ "sellerpage/stock/low-stock.fxml");
        FXRouter.when("new-order", packageStr+ "sellerpage/order/new-order.fxml");
        FXRouter.when("shipped-order", packageStr+ "sellerpage/order/shipped-order.fxml");
        FXRouter.when("add-item", packageStr+ "sellerpage/product/add-product.fxml");
        FXRouter.when("edit-add-shop", packageStr+ "sellerpage/product/edit-product.fxml");
        FXRouter.when("edit-shop", packageStr+ "sellerpage/edit-shop.fxml");

    }

    public static void main(String[] args) {
        launch();
    }

}