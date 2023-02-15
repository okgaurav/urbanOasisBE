package com.mongo.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
