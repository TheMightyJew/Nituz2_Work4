package DatabaseManager.Factories;

import Updates.UpdateData;

public class UpdateDataFactory {
    private static UpdateDataFactory ourInstance = new UpdateDataFactory();

    public static UpdateDataFactory getInstance() {
        return ourInstance;
    }

    private UpdateDataFactory() {
    }

    public UpdateData Build(int updateDataID, String data, String data_date) {
        // TODO: 6/15/2019 create the update data with all of the parameters
        return new UpdateData(data);
    }
}
