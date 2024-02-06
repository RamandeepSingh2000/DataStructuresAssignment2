package approach1.models;

public class User {
    private static int lastUserId = -1;
    private int userId;
    private String userData; //This represents username, contact info, etc.
    public User(String userData){
        userId = ++lastUserId;
        this.userData = userData;
    }
    public int getUserId() {
        return userId;
    }
    public String getUserData(){
        return userData;
    }
}
