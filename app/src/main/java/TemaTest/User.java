package TemaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SplittableRandom;

public class User {
    private String userName, password;
    private ArrayList<Post> postsList, likedPostsList;
    private ArrayList<Comment> likedCommentList;

    private ArrayList<User> following;
    public ArrayList<User> followers;

    User(String userName, String password) {
        this.userName           = userName;
        this.password           = password;
        this.following          = new ArrayList<User>();
        this.followers          = new ArrayList<User>();
        this.postsList          = new ArrayList<Post>();
        this.likedPostsList     = new ArrayList<Post>();
        this.likedCommentList   = new ArrayList<Comment>();
    }
    public String getUserName() {
        return this.userName;
    }
    public String getPassword() {
        return this.password;
    }
    public int getNumberOfFollowers() {return this.followers.size();}
    public int getTotalNumberOfLikes() {
        int sum = 0;
        for (Post i : App.getAcsBook().getPostsList()) {
            if (i.getOwner().equals(this))
                sum += i.getNumberOfLikes();
            for (Comment j : i.getCommentsList()) {
                if (j.getOwner().equals(this))
                    sum += j.getNumberOfLikes();
            }
        }
        return sum;
    }
    public ArrayList<User> getFollowing() {
        return this.following;
    }
    public ArrayList<User> getFollowers() {
        return this.followers;
    }
    public ArrayList<Post> getPostsList() {
        return this.postsList;
    }
    public ArrayList<Post> getLikedPostsList() {
        return likedPostsList;
    }
    public ArrayList<Comment> getLikedCommentList() {
        return likedCommentList;
    }

    public boolean isFollowing(String followedUser) {
        if (following.isEmpty()) return false;
        for (User i : following) {
            if (i.getUserName().equals(followedUser))
                return true;
        }
        return false;
    }
    public void addNewPost(Post post) {
        this.postsList.add(post);
    }
    public void addNewFollow(User user) {this.following.add(user);}
    public void addNewFollower(User user) {this.followers.add(user);}
    public void removeFollow(User user) {this.following.remove(user);}
    public void removeFollower(User user) {this.followers.remove(user);}
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String postsToString() {
        String list = "";
        for (Post i : postsList) {
            list += i.toStringWithoutCommentsNo() + ",";
        }
        return list;
    }
    public String followingToString() {
        String fllw = "[";
        for (User i : following) {
            fllw += "'" + i.getUserName() + "'" + ",";
        }
        if (fllw.length() > 1) {
            fllw = fllw.substring(0, fllw.length()-1);
            fllw += "]";
        }
        return fllw;
    }
    public String followersToString() {
        String fllw = "[";
        for (User i : followers) {
            fllw += "'" + i.getUserName() + "'" + ",";
        }
        if (fllw.length() > 1) {
            fllw = fllw.substring(0, fllw.length()-1);
            fllw += "]";
        }
        return fllw;
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
    public String followingsPostsToString() {
        String list = "[";
        for (Post i : App.getAcsBook().getPostsList()) {
            if (this.following.contains(i.getOwner()))
                list += i.toStringWithoutCommentsNo() + ",";
        }

        if (list.length() > 1) {
            list = list.substring(0, list.length()-1);
            list += "]";
        }
        return list;
    }


    @Override
    public String toString() {
        return "{" +
                "'username':'" + userName + "'" +
                ",'number_of_followers':'" + followers.size() + "'" +
                '}';
    }public String toStringByLikes() {
        return "{" +
                "'username':'" + userName + "'" +
                ",'number_of_likes':'" + this.getTotalNumberOfLikes() + "'" +
                '}';
    }
}
