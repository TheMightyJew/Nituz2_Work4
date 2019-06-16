package Controllers;

import DatabaseManager.RegisteredUserTableManager;
import DatabaseManager.RegularUsersTableManager;

import java.util.List;

public class ChangePasswordController extends Controller{

    public List<String> reviewPersonalInformation(String username){
        List<String> info = null;
        // TODO: 14-Jun-19
        return info;
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
