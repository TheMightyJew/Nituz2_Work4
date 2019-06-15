package DatabaseManager.Factories.RegularUserFactories;

import Organizations.Organization;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.RegularUser;

public class EmergencyCenterUserFactory implements RegularUserBuilder{
    private static EmergencyCenterUserFactory ourInstance = new EmergencyCenterUserFactory();

    public static EmergencyCenterUserFactory getInstance() {
        return ourInstance;
    }

    private EmergencyCenterUserFactory() {
    }

    @Override
    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, Organization organization){
        return new EmergencyCenterUser(null,null,Username,Mail_Address,Password, organization, Degree + "");
    }
}
