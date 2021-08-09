module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;

    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;
    opens ku.cs.login.controllers to javafx.fxml;
    exports ku.cs.admin.controllers;
    opens ku.cs.admin.controllers to javafx.fxml;
}
