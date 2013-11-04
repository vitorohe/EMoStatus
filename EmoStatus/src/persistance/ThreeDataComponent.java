package persistance;

public class ThreeDataComponent{
    private String title;
    private String description;
    private String image_path;

    public ThreeDataComponent(){

    }

    public ThreeDataComponent(String title, String description, String image_path){
        setTitle(title);
        setDescription(description);
        setImage_path(image_path);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
