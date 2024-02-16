package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Comment implements Likeable {
    private static int numberOfComments;
    private int commentID, numberOfLikes;
    private String content;
    private User owner;
    private Post commentedPost;
    private Date commentDate;

    Comment(String content, User owner, Post post) {
        numberOfComments++;
        this.content        = content;
        this.owner          = owner;
        this.commentDate    = new Date();
        this.numberOfLikes  = 0;
        this.commentID      = numberOfComments;
        this.commentedPost  = post;
    }

    public static void setNumberOfComments(int numberOfComments) {
        Comment.numberOfComments = numberOfComments;
    }

    public static int getNumberOfComments() {
        return numberOfComments;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public String commentDateToString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(commentDate);
    }
    public int getCommentID() {
        return this.commentID;
    }
    public User getOwner() {
        return this.owner;
    }

    public Post getCommentedPost() {
        return this.commentedPost;
    }
    public boolean isLikedByUser(User user) {
        if (user.getLikedCommentList().isEmpty()) return false;
        return user.getLikedCommentList().contains(this);
    }
    public boolean isOwnedByUser(User user) {
        return owner.getUserName().equals(user.getUserName());
    }
    public void incNumberOfLikes(boolean inc) {
        if (inc)
            numberOfLikes++;
        else if (numberOfLikes > 0)
            numberOfLikes--;
    }

    @Override
    public String toString() {
        return "{" +
                "'comment_id':'" + commentID + "'" +
                ",'comment_text':'" + content + "'" +
                ",'comment_date':'" + commentDateToString() + "'" +
                ",'username':'" + owner.getUserName() + "'" +
                ",'number_of_likes':'" + numberOfLikes + "'" +
                '}';
    }
}
