public interface AdminWorks extends Editable{
    void addUser(User user);
    void updateUserPrivileges(String userID, String privilege);
    void modifySettings(String setting);
}