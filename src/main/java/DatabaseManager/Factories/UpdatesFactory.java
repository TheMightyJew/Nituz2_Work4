package DatabaseManager.Factories;

import Updates.Update;

public class UpdatesFactory {
    private static UpdatesFactory ourInstance = new UpdatesFactory();

    public static UpdatesFactory getInstance() {
        return ourInstance;
    }

    private UpdatesFactory() {
    }

    public Update Build(int update_ID, int first_Update_Data, int last_Update_Data, int user_Updates, String event_Title){
        // TODO: 6/15/2019
        return null;
    }
}
