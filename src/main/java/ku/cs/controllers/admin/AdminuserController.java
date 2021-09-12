package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ku.cs.models.admin.AdminUser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminuserController implements Initializable{
    @FXML
    private VBox userList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<AdminUser> users = new ArrayList<>(adminUsers());
        for(int i=0 ;i<users.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/adminpage/admin-user-list.fxml"));
            try{
                HBox hBox = fxmlLoader.load();
                AdminuserListController adul = fxmlLoader.getController();
                adul.setData(users.get(i));
                userList.getChildren().add(hBox);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    private List<AdminUser> adminUsers(){
        List<AdminUser> ls = new ArrayList<>();
        AdminUser user = new AdminUser();

        user.setUsername("moss");
        user.setImgSrc("/images/creditpage/moss.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserstatus(true);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("nax");
        user.setImgSrc("/images/creditpage/nax.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserstatus(true);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("bam");
        user.setImgSrc("/images/creditpage/bamboo.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserstatus(true);
        ls.add(user);

        user = new AdminUser();
        user.setUsername("ploy");
        user.setImgSrc("/images/creditpage/ploy.jpg");
        user.setShopname("happyshop");
        user.setLastlogin("NaN");
        user.setUserstatus(true);
        ls.add(user);

        return ls;
    }

}
