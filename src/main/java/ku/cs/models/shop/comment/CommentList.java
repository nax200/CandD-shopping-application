package ku.cs.models.shop.comment;

import ku.cs.services.ConditionFilterer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CommentList {
    private ArrayList<Comment> comments;

    public CommentList() {comments = new ArrayList<>();}

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public int count(){ return this.comments.size();}

    public int countCommentInProduct(String productId){
        int count = 0;
        for (Comment comment : comments){
            if(comment.getIdProduct().equals(productId)){
                count += 1;
            }
        }
        return count;
    }

    public double ratingAverage(String productId){
        if (countCommentInProduct(productId)== 0) return 0.0;
        double average = 0;
        for (Comment comment : comments){
            if(comment.getIdProduct().equals(productId)){
                average += comment.getRating();
            }
        }
        return average/countCommentInProduct(productId);
    }

    public ArrayList<Comment> filter(ConditionFilterer<Comment> filterer) {
        ArrayList<Comment> filtered = new ArrayList<>();
        for (Comment comment: this.comments) {
            if (filterer.match(comment)) {
                filtered.add(comment);
            }
        }
        return filtered;
    }

    public Comment searchIdComment(String idComment){
        for(Comment comment:comments){
            if(comment.isIdComment(idComment)){
                return comment;
            }
        }
        return null;
    }
    public void sortTime(Comparator<Comment> commentComparator){ Collections.sort(this.comments,commentComparator); }

    public String toCsv(){
        String result = "";
        for (Comment comment : comments){
            result += comment.toCsv()+ "\n";
        }
        return result;
    }
}
