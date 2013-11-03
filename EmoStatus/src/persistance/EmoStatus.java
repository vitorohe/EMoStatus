package persistance;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class EmoStatus extends Application{
    private Context bsc;
    private int actualUserMonitorized;
    private List<String> usersMonitorized;

    public EmoStatus(){
        super();
        this.usersMonitorized = new ArrayList<String>();
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
        return this.usersMonitorized.get(this.actualUserMonitorized);
    }

    public List getUsersMonitorized() {
        return usersMonitorized;
    }

    public void setUsersMonitorized(List usersMonitorized) {
        this.usersMonitorized = usersMonitorized;
    }
}
