package Controllers;

import DatabaseManager.RegisteredUserTableManager;
import DatabaseManager.RegularUsersTableManager;
import Users.RegisteredUser;

import java.util.List;

public class ChangePasswordController extends Controller{

    public RegisteredUser reviewPersonalInformation(String username){
        return RegisteredUserTableManager.getInstance().getUserByUsername(username);
    }

    public boolean changePassword(String username , String oldPassowrd , String newPassword ){
        if(RegisteredUserTableManager.getInstance().CheckIfUsernameIsTaken(username)){
            if(RegisteredUserTableManager.getInstance().GetPasswordByUsername(username).equals(oldPassowrd)){
                if(Massage.confirmMassage("Are you sure you want to change the password?")){
                    RegisteredUserTableManager.getInstance().ChangePassword(username,newPassword);
                    Massage.infoMassage("Password changed successfully");
                    return true;
                }
            }
            else{
                Massage.errorMassage("Old password is incorrect!");
            }
        }
        return false;
    }
}
