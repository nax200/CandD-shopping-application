package ku.cs.models.admin;

public class AdminUserReport extends AdminUser {
    private String moreDetailReport;
    private String reportType;
    private String messagetoReport;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getMessagetoReport() {
        return messagetoReport;
    }

    public void setMessagetoReport(String messagetoReport) {
        this.messagetoReport = messagetoReport;
    }

    public void setMoreDetailReport(String moreDetailReport){
        this.moreDetailReport = moreDetailReport;
    }
    public String getMoreDetailReport(){
        return  this.moreDetailReport;
    }
}

