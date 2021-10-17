package ku.cs.models.report;

import ku.cs.models.shop.product.Product;
import ku.cs.models.shop.product.ProductList;
import ku.cs.models.user.Customer;
import ku.cs.services.DataSource;
import ku.cs.services.ProductFileDataSource;

import java.time.LocalDateTime;

public class ReportedProduct extends Report{
    private Product product;


    // ตอนอ่านจาก CSV
    public ReportedProduct(String reportId, String reportedUsername, String reporterUsername, String reportType, String detail, LocalDateTime reportedTime,Boolean isChecked,Boolean latestCase, String productID) {
        super(reportId, reportedUsername, reporterUsername, reportType, detail, reportedTime,isChecked,latestCase);
        DataSource<ProductList> dataSource;
        dataSource = new ProductFileDataSource();
        ProductList productList = dataSource.readData();
        this.product = productList.searchByID(productID);
    }


    // ตอนสร้าง obj ใหม่
    public ReportedProduct(String reportId, Customer reportedName, Customer reporterName, String reportType, String detail, Product product) {
        super(reportId, reportedName, reporterName, reportType, detail);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toCsv(){
        return "ReportedProduct,"+super.toCsv()+","+product.getID();
    }
}
