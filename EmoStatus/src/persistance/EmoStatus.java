package persistance;

import android.app.Application;
import android.content.Context;

public class EmoStatus extends Application{
    private Context bsc;
    private int userMonitorized;

    public EmoStatus(){
        super();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        bsc = getBaseContext();
    }

    public void setUserMonitorized(int userMonitorized) {
        this.userMonitorized = userMonitorized;
    }

    public int getUserMonitorized() {
        return this.userMonitorized;
    }
}
