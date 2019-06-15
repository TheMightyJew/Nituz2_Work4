package Updates;

import java.util.Date;

public class UpdateData {

    private String data;
    private Date date;

    public UpdateData(String data) {
        this.data = data;
        // TODO: 15-Jun-19  
        //this.date = Date.now or soemthing;
    }

    public String getData() {
        return data;
    }

    public Date getDate() {
        return date;
    }
}
