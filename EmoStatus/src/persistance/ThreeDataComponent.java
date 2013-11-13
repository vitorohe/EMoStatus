package persistance;

public class ThreeDataComponent{
    private String title;
    private String description;
    private int imageId;

    public ThreeDataComponent(){

    }

    public ThreeDataComponent(String title, String description, int imageId){
        setTitle(title);
        setDescription(description);
        setImageId(imageId);
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId= imageId;
    }
}
