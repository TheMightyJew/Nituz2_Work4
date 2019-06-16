package DatabaseManager.Factories;

import Updates.UpdateData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class UpdateDataFactory {
    private static UpdateDataFactory ourInstance = new UpdateDataFactory();

    public static UpdateDataFactory getInstance() {
        return ourInstance;
    }

    private UpdateDataFactory() {
    }

    public UpdateData Build(int updateDataID, String data, String data_date) {
        // TODO: 6/15/2019 create the update data with all of the parameters
        UpdateData updateData = new UpdateData(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(data_date,formatter);

        updateData.setDate(convertToDateViaInstant(dateTime));
        updateData.setID(updateDataID);
        return updateData;
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

}
