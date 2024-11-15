import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UserManager {
    private static UserManager instance;
    private List<User> users;

    private UserManager() {
        users = new ArrayList<>();
        loadUsersFromFile();
        loadAdminsFromFile();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("User authenticated as: " + user.getClass().getSimpleName());
                return user;
            }
        }
        System.out.println("Authentication failed for username: " + username);
        return null;
    }

    public void loadUsersFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("User.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String userType = data[4];
                User user;
                if ("Regular".equalsIgnoreCase(userType)) {
                    user = new RegularUser(data[1], data[2], data[3]);
                } else if ("Power".equalsIgnoreCase(userType)) {
                    user = new PowerUser(data[1], data[2], data[3]);
                } else {
                    user = new AdminUser(data[1], data[2], data[3]);
                }
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
    }

    public void saveUsersToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("User.csv"))) {
            for (User user : users) {
                String userType = user instanceof AdminUser ? "Admin" :
                        user instanceof PowerUser ? "Power" : "Regular";
                writer.write(user.userID + "," + user.getUsername() + "," + user.email + "," + user.password + "," + userType + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing user file: " + e.getMessage());
        }
    }

    public void displayUsers() {
        for (User user : users) {
            String userType = user instanceof AdminUser ? "Admin" :
                    user instanceof PowerUser ? "Power" : "Regular";
            System.out.println("UserID: " + user.userID + ", Username: " + user.getUsername() + ", Email: " + user.email + ", Type: " + userType);
        }
    }

    public void loadAdminsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Admin.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                AdminUser admin = new AdminUser(data[1], data[2], data[3]); // UserID, Username, Email, Password
                users.add(admin);
            }
        } catch (IOException e) {
            System.out.println("Error reading admin file: " + e.getMessage());
        }
    }

    public void saveAdminsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Admin.csv"))) {
            for (User user : users) {
                if (user instanceof AdminUser) {
                    writer.write(user.userID + "," + user.getUsername() + "," + user.email + "," + user.password + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing admin file: " + e.getMessage());
        }
    }



    public List<User> getUsers() {
        return users;
    }
}
