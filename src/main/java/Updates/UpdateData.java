package Updates;

import java.util.Date;

public class UpdateData {

    private int ID;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
