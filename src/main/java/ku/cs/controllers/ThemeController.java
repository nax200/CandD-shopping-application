package ku.cs.controllers;

import javafx.scene.layout.AnchorPane;

public class ThemeController {
    public static boolean isLightMode;

    public static void switchMode(AnchorPane parent){
        isLightMode = !isLightMode;
        setTheme(parent);
    }

    public static void setTheme(AnchorPane parent){
        if (isLightMode) {
            parent.getStylesheets().remove("/css-style/dark-mode.css");
            parent.getStylesheets().add("/css-style/light-mode.css");
        }else{
            parent.getStylesheets().remove("/css-style/light-mode.css");
            parent.getStylesheets().add("/css-style/dark-mode.css");
        }
    }
}
