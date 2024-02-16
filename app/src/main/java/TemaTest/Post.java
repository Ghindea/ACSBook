package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Likeable{
    private static int numberOfPosts;
    private int postID, numberOfLikes, numberOfComments;
    private String content;
    private User owner;
    private Date postDate;
    private ArrayList<Comment> commentsList;
    Post(String text, User owner) {
        numberOfPosts++;
        this.postID             = numberOfPosts;
        this.numberOfLikes      = 0;
        this.numberOfComments   = 0;
        this.content            = text;
        this.owner              = owner;
        this.postDate           = new Date(System.currentTimeMillis());
        this.commentsList       = new ArrayList<Comment>();
    }
    Post(){this(null, null);}

    public boolean isLikedByUser(User user) {
        if (user.getLikedPostsList().isEmpty()) return false;
        return user.getLikedPostsList().contains(this);
    }
    public boolean isOwnedByUser(User user) {
        return owner.getUserName().equals(user.getUserName());
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public String postDateToString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(postDate);
    }
    public User getOwner() {
        return this.owner;
    }
    public int getPostID() {
        return this.postID;
    }
    public static int getNumberOfPosts() {
        return numberOfPosts;
    }
    public int getPostsNumberOfComments() {return this.commentsList.size();}

    public Date getPostDate() {
        return this.postDate;
    }
    public ArrayList<Comment> getCommentsList() {return this.commentsList;}
    public static void setNumberOfPosts(int numberOfPosts) {
        Post.numberOfPosts = numberOfPosts;
    }
    public void incNumberOfComments(boolean inc) {
        if(inc)                     // if inc == true i increment the number of comments
            numberOfComments++;
        else if (numberOfLikes > 0) // if inc == false i decrement the number of comments
            numberOfComments--;
    }
    public void incNumberOfLikes(boolean inc) {
        if (inc)
            numberOfLikes++;
        else if (numberOfLikes > 0)
            numberOfLikes--;
    }
    public void addNewComment(Comment comment) {this.commentsList.add(comment); incNumberOfComments(true);}
    public void removeComment(Comment comment) {this.commentsList.remove(comment); incNumberOfComments(false);}

    @Override
    public String toString() {
        return  "{" +
                "'post_id':'" + postID + "'" +
                ",'post_text':'" + content + "'" +
                ",'post_date':'" + postDateToString() + "'" +
                ",'username':'" + owner.getUserName() + "'" +
                ",'number_of_comments':'" + commentsList.size() + "'" +
                '}';
    }
    public String toStringLikes() {
        return  "{" +
                "'post_id':'" + postID + "'" +
                ",'post_text':'" + content + "'" +
                ",'post_date':'" + postDateToString() + "'" +
                ",'username':'" + owner.getUserName() + "'" +
                ",'number_of_likes':'" + getNumberOfLikes() + "'" +
                '}';
    }
    public String toStringWithoutCommentsNo() {
        return  "{" +
                "'post_id':'" + postID + "'" +
                ",'post_text':'" + content + "'" +
                ",'post_date':'" + postDateToString() + "'" +
                ",'username':'" + owner.getUserName() + "'" +
                '}';
    }
    public String toStringWithoutCommentsNoAndUser() {
        return  "{" +
                "'post_id':'" + postID + "'" +
                ",'post_text':'" + content + "'" +
                ",'post_date':'" + postDateToString() + "'" +
                '}';
    }
    public String toStringButInAnotherFkdOrderCuzWhyNotCreateAConsistentFormatWhenUMakeTests() {    // no pun intended
        return  "[{" +
                "'post_text':'" + content + "'" +
                ",'post_date':'" + postDateToString() + "'" +
                ",'username':'" + owner.getUserName() + "'" +
                ",'number_of_likes':'" + numberOfLikes + "'" +
                ",'comments':" + commentsToString()  +
                "}]";
    }
    public String commentsToString() {
        String list = "[";
        for (Comment i : commentsList) {
            list += i.toString() + ",";
        }

        if (list.length() > 1) {
            list = list.substring(0, list.length()-1);
            list += "]";
        }
        return list;
    }
}
