package DatabaseManager.Factories.RegularUserFactories;

import Users.RegularUsers.RegularUser;

public interface RegularUserBuilder {
    RegularUser Build(String Username, String Password, String Mail_Address, int Degree, int Status, String Type, String Organization_Name);
}
