package meTube.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="tubes")
public class Tube extends BaseEntity {
    private String title;
    private String author;
    private String youtubeLink;
    private String description;
    private int views;
    private User uploader;

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NotNull
    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
