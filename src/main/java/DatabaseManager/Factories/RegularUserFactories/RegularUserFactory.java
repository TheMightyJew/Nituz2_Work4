package DatabaseManager.Factories.RegularUserFactories;

import Users.RegularUsers.RegularUser;

public class RegularUserFactory implements RegularUserBuilder {
    private static RegularUserFactory ourInstance = new RegularUserFactory();

    public static RegularUserFactory getInstance() {
        return ourInstance;
    }

    private RegularUserFactory() {
    }

    @Override
    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, String Organization_Name){
        if(Type.equals("Emergency_Center")){
            return EmergencyCenterUserFactory.getInstance().Build(Username, Password, Mail_Address, Degree, Status, Type, Organization_Name);
        }
        else{
            //if(Type.equals("Security_Force"))
            return SecurityForceUserFactory.getInstance().Build(Username, Password, Mail_Address, Degree, Status, Type, Organization_Name);
        }
    }
}
