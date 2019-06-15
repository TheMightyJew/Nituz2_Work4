package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;

public interface AdminBuilder {
    Admin Build(String Username, String Password, String Mail_Address, String Type);
}
