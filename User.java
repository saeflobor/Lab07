import java.util.UUID;

public abstract class User {
    protected String username;
    protected String userID;
    protected String email;
    protected String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.userID = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public abstract void performActions(String action);
}
