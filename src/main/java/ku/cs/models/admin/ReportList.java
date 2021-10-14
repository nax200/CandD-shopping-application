package ku.cs.models.admin;

import ku.cs.services.ConditionFilterer;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reports;

    public ReportList(){reports = new ArrayList<>();}

    public void  addReport(Report report){
        this.reports.add(report);
    }
    public int count(){
        return this.reports.size();
    }
    public Report searchReport(String reportId){
        for(Report report : reports){
            if(report.getReportId().equals(reportId)) {
                return report;
            }
        }
        return null;
    }
    public String searchAllCheckedCase(String userName){
        String result = "";
        for (Report report : this.reports){
            if((report.getReportedName().getUsername()).equals(userName) && report.getChecked() && report.getRecentCase()){
                result += report.getReportType() + "\n";
            }
        }
        return  result;
    }

    public void searchCaseReportToSetLatest(String userName){
        for (Report report : this.reports){
            if(report.getReportedName().getUsername().equals(userName) && report.getChecked()){
                report.setRecentCase(false);
            }
        }
    }

    public ArrayList<Report> filter(ConditionFilterer<Report> filterer) {
        ArrayList<Report> filtered = new ArrayList<>();
        for (Report report : this.reports) {
            if (filterer.match(report)) {
                filtered.add(report);
            }
        }
        return filtered;
    }

    public  String toCsv() {
        String result = "";
        for (Report report : reports){
            result += report.toCsv() +"\n";
        }
        return result;
    }
}

