package Updates;

import java.util.Date;

public class UpdateData {

    private String data;
    private Date date;

    public UpdateData(String data) {
        this.data = data;
        this.date = new Date();
    }

    public String getData() {
        return data;
    }

    public Date getDate() {
        return date;
    }
}
