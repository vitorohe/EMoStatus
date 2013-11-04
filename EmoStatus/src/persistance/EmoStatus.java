package persistance;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class EmoStatus extends Application{
    private Context bsc;
    private int actualUserMonitorized;
    private List<ThreeDataComponent> usersMonitorized;

    public EmoStatus(){
        super();
        this.usersMonitorized = new ArrayList<ThreeDataComponent>();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        bsc = getBaseContext();
    }

    public void setActualUserMonitorized(int actualUserMonitorized) {
        this.actualUserMonitorized = actualUserMonitorized;
    }

    public String getActualUserMonitorized() {
        return this.usersMonitorized.get(this.actualUserMonitorized).getTitle();
    }

    public List<ThreeDataComponent> getUsersMonitorized() {
        return usersMonitorized;
    }

    public void setUsersMonitorized(List<ThreeDataComponent> usersMonitorized) {
        this.usersMonitorized = usersMonitorized;
    }

    public List<HistoryDay> getHistoryActualUserMonitorized() {
        List<HistoryDay> history = new ArrayList<HistoryDay>();

        HistoryDay h1 = new HistoryDay();
        HistoryDay h2 = new HistoryDay();
        HistoryDay h3 = new HistoryDay();

        List<ThreeDataComponent> h1Data = new ArrayList<ThreeDataComponent>();
        List<ThreeDataComponent> h2Data = new ArrayList<ThreeDataComponent>();
        List<ThreeDataComponent> h3Data = new ArrayList<ThreeDataComponent>();

        h1.setDay_date("Hoy");
        h1Data.add(new ThreeDataComponent("Title1","Desc1",""));
        h1Data.add(new ThreeDataComponent("Title2","Desc2",""));
        h1Data.add(new ThreeDataComponent("Title3","Desc3",""));
        h1.setHistory(h1Data);

        h2.setDay_date("Ayer");
        h2Data.add(new ThreeDataComponent("Title1","Desc1",""));
        h2Data.add(new ThreeDataComponent("Title2","Desc2",""));
        h2Data.add(new ThreeDataComponent("Title3","Desc3",""));
        h2.setHistory(h2Data);

        h3.setDay_date("Jueves 31 Octubre 2013");
        h3Data.add(new ThreeDataComponent("Title1","Desc1",""));
        h3Data.add(new ThreeDataComponent("Title2","Desc2",""));
        h3Data.add(new ThreeDataComponent("Title3","Desc3",""));
        h3.setHistory(h3Data);

        history.add(h1);
        history.add(h2);
        history.add(h3);

        return history;
    }
}
