package com.mongo.backend.model.entity;

import com.mongo.backend.config.State;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comments {
    @Id
    private String uniqueId;
    private String accountId;
    private String productUniqueId;
    private String dateTime;
    private String commentText;
    private String rating;
    private List<String> images;
    private State state;
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public Comments setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Comments setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public Comments setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getProductUniqueId() {
        return productUniqueId;
    }

    public Comments setProductUniqueId(String productUniqueId) {
        this.productUniqueId = productUniqueId;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Comments setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getCommentText() {
        return commentText;
    }

    public Comments setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public Comments setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public Comments setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public State getState() {
        return state;
    }

    public Comments setState(State state) {
        this.state = state;
        return this;
    }
}
