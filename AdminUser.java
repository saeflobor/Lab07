public class AdminUser extends User implements AdminWorks{
    public AdminUser(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public void viewData() {

    }

    @Override
    public void editUserData(String data) {

    }

    @Override
    public void addUser(User user) {
        UserManager userManager = UserManager.getInstance();

        // Ensure username and email are unique
        for (User existingUser : userManager.getUsers()) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                System.out.println("Error: Username already exists.");
                return;
            }
            if (existingUser.email.equals(user.email)) {
                System.out.println("Error: Email already exists.");
                return;
            }
        }

        userManager.getUsers().add(user); // Add user to the list
        userManager.saveUsersToFile();    // Persist changes
        System.out.println("User " + user.getUsername() + " added successfully by admin.");
    }

    @Override
    public void updateUserPrivileges(String userID, String privilege) {
    }

    @Override
    public void modifySettings(String setting) {

    }

    @Override
    public void performActions(String action) {

    }
}