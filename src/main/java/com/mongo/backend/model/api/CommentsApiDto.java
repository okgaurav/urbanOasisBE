package com.mongo.backend.model.api;

import com.mongo.backend.config.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsApiDto {
    @Id
    private String uniqueId;
    private String accountId;
    private String productUniqueId;
    private String dateTime;
    private String commentText;
    private Integer rating;
    private List<String> images;
    private State state;
    private Integer version;
    public Integer getVersion() {
        return version;
    }
    public CommentsApiDto setVersion(Integer version) {
        this.version = version;
        return this;
    }
    public String getUniqueId() {
        return uniqueId;
    }

    public CommentsApiDto setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public CommentsApiDto setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getProductUniqueId() {
        return productUniqueId;
    }

    public CommentsApiDto setProductUniqueId(String productUniqueId) {
        this.productUniqueId = productUniqueId;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public CommentsApiDto setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentsApiDto setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public CommentsApiDto setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public CommentsApiDto setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public State getState() {
        return state;
    }

    public CommentsApiDto setState(State state) {
        this.state = state;
        return this;
    }
}
