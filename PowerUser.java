public class PowerUser extends User implements Editable{
    public PowerUser(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public void viewData() {

    }

    @Override
    public void editUserData(String data) {

    }

    @Override
    public void performActions(String action) {
        if (action.equals("view")) {
            viewData();
        }
        if (action.equals("edit")) {
            editUserData("data");
        }
    }
}
