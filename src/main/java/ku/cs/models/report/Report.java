package ku.cs.models.report;

import ku.cs.models.shop.comment.Comment;
import ku.cs.models.user.Customer;
import ku.cs.models.user.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.UserFileDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private String reportId;
    private Customer reportedName;
    private Customer reporterName;
    private String reportType;
    private String detail;
    private LocalDateTime reportedTime;
    private Boolean isChecked;
    private Boolean recentCase;

    // ตอนอ่านจาก CSV
    public Report(String reportId, String reportedUsername, String reporterUsername, String reportType, String detail, LocalDateTime reportedTime,Boolean isChecked,Boolean recentCase) {
        DataSource<UserList> dataSource;
        dataSource = new UserFileDataSource();
        UserList userList = dataSource.readData();

        this.reportId = reportId;
        this.reportedName = (Customer) userList.searchUsername(reportedUsername);
        this.reporterName = (Customer) userList.searchUsername(reporterUsername);
        this.reportType = reportType;
        this.detail = detail;
        this.reportedTime = reportedTime;
        this.isChecked = isChecked;
        this.recentCase = recentCase;
    }

    // ตอนสร้าง report ใหม่
    public Report(String reportId, Customer reportedName, Customer reporterName, String reportType, String detail) {
        this.reportId = reportId;
        this.reportedName = reportedName;
        this.reporterName = reporterName;
        this.reportType = reportType;
        this.detail = detail;
        this.reportedTime = LocalDateTime.now();
        this.isChecked = false;
        this.recentCase = true;
    }

    public String getReportId() {
        return reportId;
    }

    public Customer getReportedName() {
        return reportedName;
    }

    public Customer getReporterName() {
        return reporterName;
    }

    public String getDetail() {
        return detail;
    }

    public String getReportType(){ return reportType; }

    public LocalDateTime getReportedTime() {
        return reportedTime;
    }

    public String getReportTimeToString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(reportedTime);
    }
    public Comment getComment(){
        return null;
    }

    public Boolean getChecked() {
        return isChecked;
    }
    public Boolean getRecentCase(){ return recentCase; }

    public void setIsChecked(){this.isChecked = true;}

    public void setRecentCase(Boolean recentCase){this.recentCase = recentCase;}

    public String toCsv(){
         return getReportId()+","+reportedName.getUsername()+","+reporterName.getUsername()+","+getReportType()+","+getDetail()+","+getReportTimeToString()+","+ getChecked()+","+ getRecentCase();
    }
}
