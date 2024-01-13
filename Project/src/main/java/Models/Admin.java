package Models;

public class Admin {
    private String UserName;
    private String Password;
    private String Type;

    public Admin(String userName, String password, String type) {
        UserName = userName;
        Password = password;
        Type = type;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getType() {
        return Type;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setType(String type) {
        Type = type;
    }
}
