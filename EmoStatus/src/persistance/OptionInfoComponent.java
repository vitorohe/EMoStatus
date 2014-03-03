package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class OptionInfoComponent extends InfoComponent{
    private String info;

    public OptionInfoComponent(String title, String info, int id, boolean enabled){
        setTitle(title);
        setInfo(info);
        setId(id);
        setEnabled(enabled);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info= info;
    }
}
