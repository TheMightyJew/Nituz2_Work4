package Controllers;

import DatabaseManager.RegisteredUserTableManager;
import DatabaseManager.RegularUsersTableManager;
import Users.RegisteredUser;

import java.util.List;

public class ChangePasswordController extends Controller{

    public RegisteredUser reviewPersonalInformation(String username){
        try{
            return RegisteredUserTableManager.getInstance().getUserByUsername(username);
        }
        catch (Exception e){
            return null;
        }


    }

    public boolean changePassword(String username , String oldPassowrd , String newPassword ){
        if(RegisteredUserTableManager.getInstance().CheckIfUsernameIsTaken(username)){
            if(RegisteredUserTableManager.getInstance().GetPasswordByUsername(username).equals(oldPassowrd)){
                RegisteredUserTableManager.getInstance().ChangePassword(username,newPassword);
                return true;
            }
        }
        return false;
    }
}
