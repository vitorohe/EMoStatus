package persistance;

/**
 * Created by vito on 09-11-13.
 */
public class OptionComponent {
    private String title;
    private String iconPath;
    private Boolean checked;

    public OptionComponent(){

    }

    public OptionComponent(String title, String iconPath, Boolean checked){
        setTitle(title);
        setIconPath(iconPath);
        setChecked(checked);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
