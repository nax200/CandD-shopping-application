package ku.cs.models.report;

import ku.cs.models.shop.comment.Comment;
import ku.cs.models.shop.comment.CommentList;
import ku.cs.models.user.Customer;
import ku.cs.services.CommentFileDataSource;
import ku.cs.services.DataSource;

import java.time.LocalDateTime;

public class ReportedComment extends Report{
    private Comment comment;


    public ReportedComment(String reportId, String reportedUsername, String reporterUsername, String reportType, String detail, LocalDateTime reportedTime,Boolean isChecked,Boolean latestCase,String idComment) {
        super(reportId, reportedUsername, reporterUsername, reportType, detail, reportedTime,isChecked,latestCase);
        DataSource<CommentList> dataSource;
        dataSource = new CommentFileDataSource();
        CommentList commentList = dataSource.readData();
        this.comment = commentList.searchIdComment(idComment);
    }

    public ReportedComment(String reportId, Customer reportedName, Customer reporterName, String reportType, String detail, Comment comment) {
        super(reportId, reportedName, reporterName, reportType, detail);
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    @Override
    public String toCsv(){
        return  "ReportedComment,"+super.toCsv()+","+comment.getIdComment();
    }
}
