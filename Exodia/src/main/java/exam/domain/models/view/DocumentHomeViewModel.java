package exam.domain.models.view;

public class DocumentHomeViewModel {
    private String id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length() > 12) {
            title = title.substring(0, 12).concat("...");
        }
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
