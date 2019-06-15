package DatabaseManager.Factories.RegularUserFactories;

import Users.RegularUsers.RegularUser;

public class EmergencyCenterUserFactory implements RegularUserBuilder{
    private static EmergencyCenterUserFactory ourInstance = new EmergencyCenterUserFactory();

    public static EmergencyCenterUserFactory getInstance() {
        return ourInstance;
    }

    private EmergencyCenterUserFactory() {
    }

    @Override
    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, String Organization_Name){
        // TODO: 6/15/2019
        return null;
    }
}
