package ku.cs.models.shop;

import ku.cs.services.CommentFileDataSource;
import ku.cs.services.DataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private String idComment;
    private String idProduct;
    private String nameToComment;
    private String comment;
    private LocalDateTime timeToComment;
    private int rating;

    public Comment(String idComment, String idProduct, String nameToComment, String comment, LocalDateTime timeToComment,int rating) {
        this.idComment = idComment;
        this.idProduct = idProduct;
        this.nameToComment = nameToComment;
        this.comment = comment;
        this.timeToComment = timeToComment;
        this.rating = rating;
    }

    public String getIdComment() { return idComment; }

    public String getIdProduct() {
        return idProduct;
    }

    public String getNameToComment() {
        return nameToComment;
    }


    public String getComment() {
        return comment;
    }

    public LocalDateTime getTimeToComment() {
        return timeToComment;
    }

    public int getRating (){ return  rating ;}

    public String getTimeToCommentToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(timeToComment);
    }

    public Boolean isIdComment(String idComment){
         return this.idComment.equals(idComment);
    }

    public String toCsv(){
        return idComment + "," + idProduct + "," + nameToComment + "," + comment + "," + getTimeToCommentToString() + "," + rating;
    }
}
