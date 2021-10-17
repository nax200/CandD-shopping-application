package ku.cs.controllers.market.productdetail;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ku.cs.controllers.ThemeController;
import ku.cs.models.report.ReportList;
import ku.cs.models.report.ReportedComment;
import ku.cs.models.report.ReportedProduct;
import ku.cs.models.shop.comment.Comment;
import ku.cs.models.shop.product.Product;
import ku.cs.models.user.Customer;
import ku.cs.services.DataSource;
import ku.cs.services.ReportFileDataSource;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportPopupController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private ComboBox<String> reportTypeComboBox;
    @FXML private TextArea detailTextArea;
    @FXML private AnchorPane parent;

    private Customer reported;
    private Customer reporter;
    private Comment comment;
    private Product product;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemeController.setTheme(parent);
    }

    public void setData(Customer reported, Customer reporter, Object object, Stage stage){

        this.reported = reported;
        this.reporter = reporter;
        if(object instanceof Comment) {
            this.comment = (Comment)object;
            reportTypeComboBox.getItems().addAll("เนื้อหาไม่เหมาะสม","ความคิดเห็นไม่เหมาะสม",
                    "ข่าวปลอม","เนื้อหาล่อแหลม","เนื้อหาอันตรายมีความรุนแรง","อื่น ๆ");
            nameTextField.setText( reported.getName() );
        }
        if(object instanceof Product){
            this.product = (Product) object;
            reportTypeComboBox.getItems().addAll("สินค้าละเมิดลิขสิทธิ์","รายละเอียดสินค้าไม่เหมาะสม",
                    "สินค้าต้องห้าม","สินค้าที่หลอกลวง","สินค้าที่ส่งผลกระทบต่อบุคคล","อื่น ๆ");
            nameTextField.setText( product.getName() );
        }
        this.stage = stage;
    }

    public void confirmButton() {
        if(reportTypeComboBox.getValue() == null){
            return ;
        }
        if(detailTextArea.getText().trim().equals("")){
            detailTextArea.setText("ไม่มีรายละเอียดเพิ่มเติม");
        }
        DataSource<ReportList> dataSource;
        dataSource = new ReportFileDataSource();
        ReportList reportList = dataSource.readData();
        String id = "RP"+String.format("%05d",reportList.count()+1);
        if(comment != null) {
            reportList.addReport( new ReportedComment(id, reported, reporter, reportTypeComboBox.getValue(), detailTextArea.getText().trim(), comment));
        }else if(product !=null){
            reportList.addReport(new ReportedProduct(id, reported, reporter, reportTypeComboBox.getValue(), detailTextArea.getText().trim(), product));
        }
        dataSource.writeData(reportList);
        stage.close();
    }

    public void cancelButton() {
        stage.close();
    }


}

