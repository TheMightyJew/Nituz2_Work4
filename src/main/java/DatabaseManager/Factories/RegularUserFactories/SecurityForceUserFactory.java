package DatabaseManager.Factories.RegularUserFactories;

import Users.RegularUsers.RegularUser;

public class SecurityForceUserFactory implements RegularUserBuilder{
    private static SecurityForceUserFactory ourInstance = new SecurityForceUserFactory();

    public static SecurityForceUserFactory getInstance() {
        return ourInstance;
    }

    private SecurityForceUserFactory() {
    }

    @Override
    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, String Organization_Name){
        // TODO: 6/15/2019
        return null;
    }
}
