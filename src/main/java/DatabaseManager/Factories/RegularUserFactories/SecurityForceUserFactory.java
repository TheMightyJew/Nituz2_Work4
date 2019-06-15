package DatabaseManager.Factories.RegularUserFactories;

import Organizations.Organization;
import Users.RegularUsers.RegularUser;
import Users.RegularUsers.SecurityForceUser;

public class SecurityForceUserFactory implements RegularUserBuilder {
    private static SecurityForceUserFactory ourInstance = new SecurityForceUserFactory();

    public static SecurityForceUserFactory getInstance() {
        return ourInstance;
    }

    private SecurityForceUserFactory() {
    }

    @Override
    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, Organization organization){
        return new SecurityForceUser(null, Username, Mail_Address, Password, organization, Degree + "");
    }
}
