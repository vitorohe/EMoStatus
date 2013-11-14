package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class OptionInfoComponent {
    private String title;
    private String info;
    private int id;
    private boolean enabled;

    public OptionInfoComponent(){

    }

    public OptionInfoComponent(String title, String info, int id, boolean enabled){
        setTitle(title);
        setInfo(info);
        setId(id);
        setEnabled(enabled);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info= info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
