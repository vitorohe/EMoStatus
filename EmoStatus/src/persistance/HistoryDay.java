package persistance;

import java.util.List;

public class HistoryDay {

    private String day_date;
    private List<ThreeDataComponent> history;

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

}
