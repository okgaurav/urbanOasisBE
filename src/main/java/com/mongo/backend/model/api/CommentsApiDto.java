package com.mongo.backend.model.api;

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
    private String rating;
    private List<String> images;
}
