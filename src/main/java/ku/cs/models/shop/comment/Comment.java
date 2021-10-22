package ku.cs.models.shop.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private String idComment;
    private String idProduct;
    private String sender;
    private String comment;
    private LocalDateTime commentTime;
    private int rating;
    private Boolean visible;

    public Comment(String idComment, String idProduct, String sender, String comment, LocalDateTime commentTime, int rating, Boolean visible) {
        this.idComment = idComment;
        this.idProduct = idProduct;
        this.sender = sender;
        this.comment = comment;
        this.commentTime = commentTime;
        this.rating = rating;
        this.visible = visible;
    }

    public String getIdComment() { return idComment; }

    public String getIdProduct() {
        return idProduct;
    }

    public String getSender() {
        return sender;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public int getRating (){ return  rating ;}

    public Boolean getVisible(){return  visible;}

    public String getTimeToCommentToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dtf.format(commentTime);
    }

    public Boolean isIdComment(String idComment){
         return this.idComment.equals(idComment);
    }

    public void setInvisible(){
        this.visible = false;
    }

    public String toCsv(){
        return idComment + "," + idProduct + "," + sender + "," + comment + "," + getTimeToCommentToString() + "," + rating+","+visible;
    }
}
