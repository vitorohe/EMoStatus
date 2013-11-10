package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class OptionInfoComponent {
    private String title;
    private String info;
    private int id;

    public OptionInfoComponent(){

    }

    public OptionInfoComponent(String title, String info, int id){
        setTitle(title);
        setInfo(info);
        setId(id);
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
}
