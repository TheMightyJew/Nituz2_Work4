package Users;

import Organizations.Organization;

public abstract class RegisteredUser {

    enum UserStatus{
        Active,Passive,Blocked;
    }

    protected String username;
    protected String mailAddress;
    protected String password;
    protected Organization organization;

    public RegisteredUser(String username, String mailAddress, String password) {
        this.username = username;
        this.mailAddress = mailAddress;
        this.password = password;
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
