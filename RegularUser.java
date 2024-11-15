public class RegularUser extends User implements Viewable{
    public RegularUser(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public void viewData() {
    }

    @Override
    public void performActions(String action) {
        viewData();
    }
}
