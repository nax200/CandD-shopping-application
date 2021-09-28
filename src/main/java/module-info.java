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

}
