package DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories;

import DatabaseManager.Factories.OrganizationFactories.OrganizationBuilder;
import Organizations.Organization;

public class SecurityForceFactory implements OrganizationBuilder {
    private static SecurityForceFactory ourInstance = new SecurityForceFactory();

    public static SecurityForceFactory getInstance() {
        return ourInstance;
    }

    private SecurityForceFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        Organization organization;
        if(Type.equals("Security_Force_Police")){
            organization = PoliceFactory.getInstance().Build(Name, Type, Admin_Username);
        }
        else if(Type.equals("Security_Force_Fire_Fighters")){
            organization = FireFightersFactory.getInstance().Build(Name, Type, Admin_Username);
        }
        else{
            //if(Type.equals("Security_Force_Red_Cross")
            organization = RedCrossFactory.getInstance().Build(Name, Type, Admin_Username);
        }
        return organization;
    }
}
