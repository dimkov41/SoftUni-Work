package meTube.domain.models.binding;

import meTube.domain.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TubeUploadBindingModel {
    private String title;
    private String author;
    private String youtubeLink;
    private String description;
    private String uploader;

    public TubeUploadBindingModel(String title, String author, String youtubeLink, String description, String uploader) {
        this.title = title;
        this.author = author;
        this.youtubeLink = youtubeLink;
        this.description = description;
        this.uploader = uploader;
    }

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
    @Pattern(regexp = "https://www\\.youtube\\.com/watch\\?v=[\\w-_*]{11}")
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

    @NotNull
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
