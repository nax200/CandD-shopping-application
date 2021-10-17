module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.controllers.login;
    opens ku.cs.controllers.login to javafx.fxml;

    exports ku.cs.controllers.market;
    opens ku.cs.controllers.market to javafx.fxml;

    exports ku.cs.controllers.admin;
    opens ku.cs.controllers.admin to javafx.fxml;

    exports ku.cs.controllers.seller;
    opens ku.cs.controllers.seller to javafx.fxml;
    exports ku.cs.controllers.market.productdetail;
    opens ku.cs.controllers.market.productdetail to javafx.fxml;
    exports ku.cs.controllers.market.marketplace;
    opens ku.cs.controllers.market.marketplace to javafx.fxml;
    exports ku.cs.controllers.market.purchase;
    opens ku.cs.controllers.market.purchase to javafx.fxml;
    exports ku.cs.controllers.admin.customerlist;
    opens ku.cs.controllers.admin.customerlist to javafx.fxml;
    exports ku.cs.controllers.admin.blocklist;
    opens ku.cs.controllers.admin.blocklist to javafx.fxml;
    exports ku.cs.controllers.admin.report.customer;
    opens ku.cs.controllers.admin.report.customer to javafx.fxml;
    exports ku.cs.controllers.admin.report.product;
    opens ku.cs.controllers.admin.report.product to javafx.fxml;
    exports ku.cs.controllers.seller.product;
    opens ku.cs.controllers.seller.product to javafx.fxml;
    exports ku.cs.controllers.seller.stock;
    opens ku.cs.controllers.seller.stock to javafx.fxml;
    exports ku.cs.controllers.seller.order;
    opens ku.cs.controllers.seller.order to javafx.fxml;

}
