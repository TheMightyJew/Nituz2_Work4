package Updates;

import java.util.Date;

public class UpdateData {

    private String data;
    private Date date;

    public UpdateData(String data, Date date) {
        this.data = data;
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public Date getDate() {
        return date;
    }
}
