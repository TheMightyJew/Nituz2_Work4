package Users;

import Controllers.ChangePasswordController;
import Organizations.Organization;

public abstract class RegisteredUser {

    public enum UserStatus{
        Active,Passive,Blocked;
    }

    protected ChangePasswordController changePasswordController;
    protected String username;
    protected String mailAddress;
    protected String password;
    protected Organization organization;
    protected UserStatus status;

    public RegisteredUser(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization) {
        this.changePasswordController = changePasswordController;
        this.username = username;
        this.mailAddress = mailAddress;
        this.password = password;
        this.organization = organization;
        this.status = UserStatus.Active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // TODO: 14-Jun-19
        this.password = password;
    }
}
