public class Main {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();

        // Create users
        AdminUser adminUser = new AdminUser("admin", "admin@example.com", "password3");
        RegularUser regularUser = new RegularUser("user1", "user1@example.com", "password1");
        PowerUser powerUser = new PowerUser("user2", "user2@example.com", "password2");

        // Admin adds users
        adminUser.addUser(regularUser);
        adminUser.addUser(powerUser);

        // Display all users
        System.out.println("\nAll Users:");
        for (User user : userManager.getUsers()) {
            String userType = user instanceof AdminUser ? "Admin" :
                    user instanceof PowerUser ? "Power" : "Regular";
            System.out.println("UserID: " + user.userID + ", Username: " + user.getUsername() + ", Email: " + user.email + ", Type: " + userType);
        }

        // Authenticate and perform actions
        User user1 = userManager.authenticate("user1", "password1");
        if (user1 instanceof Viewable) ((Viewable) user1).viewData();

        User user2 = userManager.authenticate("user2", "password2");
        if (user2 instanceof Editable) ((Editable) user2).editUserData("New Data");

        User admin = userManager.authenticate("admin", "password3");
        if (admin instanceof AdminWorks) ((AdminWorks) admin).modifySettings("System Setting");
    }
}
