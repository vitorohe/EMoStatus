package persistance;

import java.util.Date;
import java.util.List;

public class HistoryDay {

    private String day_date;
    private Date date;
    private List<ThreeDataComponent> history;
    private boolean wasSad;

    public HistoryDay(){

    }

    public String getDay_date() {
        return day_date;
    }

    public void setDay_date(String day_date) {
        this.day_date = day_date;
    }

    public List<ThreeDataComponent> getHistory() {
        return history;
    }

    public void setHistory(List<ThreeDataComponent> history) {
        this.history = history;
    }

    public boolean wasSad(){
        return wasSad;
    }
    public void setWasSad(boolean wasSad){
        this.wasSad = wasSad;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
