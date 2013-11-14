package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class InfoComponent {
    private String title;
    private int id;
    private boolean enabled;

    public InfoComponent(){

    }

    public InfoComponent(String title, int id, boolean enabled){
        setTitle(title);
        setId(id);
        setEnabled(enabled);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
