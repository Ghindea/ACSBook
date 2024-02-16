package TemaTest;

import java.util.ArrayList;
import java.util.Collections;

public class Database {
    private ArrayList<User> usersList;
    private ArrayList<Post> postsList;
    private ArrayList<Comment> commentsList;
    Database(){
        this.usersList          = new ArrayList<User>();
        this.postsList          = new ArrayList<Post>();
        this.commentsList       = new ArrayList<Comment>();
    }

    public ArrayList<User> getUsersList() {
        return this.usersList;
    }
    public ArrayList<Post> getPostsList() {
        return this.postsList;
    }
    public ArrayList<Comment> getCommentsList() {
        return this.commentsList;
    }
    public boolean userExists(String userName) {    // checks if user exists in database
        if (usersList.isEmpty()) return false;
        for (User i: this.usersList) {
            if (i.getUserName().equals(userName))
                return true;
        }
        return false;
    }
    public boolean postExists(int ID) {
        if (postsList.isEmpty()) return false;
        for (Post i : postsList) {
            if (i.getPostID() == ID)
                return true;
        }
        return false;
    }
    public boolean commentExists(int ID) {
        if (commentsList.isEmpty()) return false;
        for (Comment i : commentsList) {
            if (i.getCommentID() == ID)
                return true;
        }
        return false;
    }
    public String getPasswordOfUser(String userName) {
        for (User i: this.usersList) {
            if (i.getUserName().equals(userName))
                return i.getPassword();
        }
        return null;
    }
    public User getUserByName(String userName) {
        for (User i : usersList) {
            if (i.getUserName().equals(userName))
                return i;
        }
        return null;
    }
    public Post getPostByID(int ID) {
        for (Post i : postsList) {
            if (i.getPostID() == ID)
                return i;
        }
        return null;
    }
    public Comment getCommentbyID(int ID) {
        for (Comment i : commentsList) {
            if (i.getCommentID() == ID)
                return i;
        }
        return null;
    }
    public void addNewUser(String userName, String password){  // add a new user in database
            this.usersList.add(new User(userName, password));
    }
    public void addNewPost(String text, User owner) {
        this.postsList.add(new Post(text, owner));
    }
    public void addNewComment(String text, User owner, Post post) {
        this.commentsList.add(new Comment(text, owner, post));
    }

    public String top5PostsToStringByNoOfComments() {
        String top = "[";
        int size = Math.min(postsList.size(), 5);

        for (int i = 0; i < size; i++) {
            top += postsList.get(i).toString() + ",";
        }

        if (top.length() > 1) {
            top = top.substring(0, top.length()-1);
            top += "]";
        }
        return top;
    }
    public String top5PostsToStringByNoOfLikes() {
        String top = "[";
        int size = Math.min(postsList.size(), 5);

        for (int i = 0; i < size; i++) {
            top += postsList.get(i).toStringLikes() + ",";
        }

        if (top.length() > 1) {
            top = top.substring(0, top.length()-1);
            top += "]";
        }
        return top;
    }
    public String top5UsersToString() {
        String top = "[";
        int size = Math.min(usersList.size(), 5);

        for (int i = 0; i < size; i++) {
            top += usersList.get(i).toString() + ",";
        }

        if (top.length() > 1) {
            top = top.substring(0, top.length()-1);
            top += " ]";
        }
        return top;
    }
    public String top5UsersByNoOfLikesToString() {
        String top = "[";
        int size = Math.min(usersList.size(), 5);

        for (int i = 0; i < size; i++) {
            top += usersList.get(i).toStringByLikes() + ",";
        }

        if (top.length() > 1) {
            top = top.substring(0, top.length()-1);
            top += " ]";
        }
        return top;
    }
    public void sortPostsByDate() {
        for (int i = 0; i < postsList.size() - 1; i++) {
            for (int j = i+1; j < postsList.size(); j++) {
                if (postsList.get(i).getPostDate().before(postsList.get(j).getPostDate()))
                    Collections.swap(postsList, i, j);
                else if (postsList.get(i).getPostDate().equals(postsList.get(j).getPostDate())) {
                    if (postsList.get(i).getPostID() < postsList.get(j).getPostID())
                        Collections.swap(postsList, i, j);
                }
            }
        }
    }
}
