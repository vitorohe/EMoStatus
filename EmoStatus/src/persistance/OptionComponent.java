package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class OptionComponent {
    private String title;
    private int iconId;
    private Boolean checked;

    public OptionComponent(){

    }

    public OptionComponent(String title, int iconId, Boolean checked){
        setTitle(title);
        setIconPath(iconId);
        setChecked(checked);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconPath(int iconId) {
        this.iconId = iconId;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
