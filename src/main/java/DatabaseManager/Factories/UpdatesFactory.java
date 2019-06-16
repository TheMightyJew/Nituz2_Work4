package DatabaseManager.Factories;

import DatabaseManager.UpdateDataTableManager;
import DatabaseManager.UserAtEventTableManager;
import Events.Event;
import Updates.Update;
import Updates.UpdateData;
import Updates.UserUpdates;

public class UpdatesFactory {
    private static UpdatesFactory ourInstance = new UpdatesFactory();

    public static UpdatesFactory getInstance() {
        return ourInstance;
    }

    private UpdatesFactory() {
    }

    public Update Build(int update_ID, int first_Update_Data, int last_Update_Data, int user_Updates){
        UpdateData first = UpdateDataTableManager.getInstance().getUpdateDataByID(first_Update_Data);
        UpdateData last = UpdateDataTableManager.getInstance().getUpdateDataByID(last_Update_Data);
        Update update = new Update(null,first);
        update.setID(update_ID);
        if(last != null)
            update.setData(last);
        return update;
    }

    public Update Build(int update_ID, int first_Update_Data, int last_Update_Data, int user_Updates, String event_title){
        // TODO: 6/15/2019
        return null;
    }
}
