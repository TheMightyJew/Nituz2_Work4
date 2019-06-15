package DatabaseManager.Factories.RegularUserFactories;

import DatabaseManager.OrganizationsTableManager;
import Organizations.Organization;
import Users.RegularUsers.RegularUser;

public class RegularUserFactory {
    private static RegularUserFactory ourInstance = new RegularUserFactory();

    public static RegularUserFactory getInstance() {
        return ourInstance;
    }

    private RegularUserFactory() {
    }

    public RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, String Organization_Name){
        Organization organization = OrganizationsTableManager.getInstance().getOrganizationByName(Organization_Name);
        if(Type.equals("Emergency_Center")){
            return EmergencyCenterUserFactory.getInstance().Build(Username, Password, Mail_Address, Degree, Status, Type, organization);
        }
        else{
            //if(Type.equals("Security_Force"))
            return SecurityForceUserFactory.getInstance().Build(Username, Password, Mail_Address, Degree, Status, Type, organization);
        }
    }
}
