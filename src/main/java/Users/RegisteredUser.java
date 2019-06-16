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
    protected String degree;


    public RegisteredUser(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization,String degree) {
        this.changePasswordController = changePasswordController;
        this.username = username;
        this.mailAddress = mailAddress;
        this.password = password;
        this.organization = organization;
        this.status = UserStatus.Active;
        this.degree = degree;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
