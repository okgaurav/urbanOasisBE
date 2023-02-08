package com.mongo.backend.model.entity.fashion;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class FashionCategory {
    @Id
    private String uniqueId;
    private Fashion brandName;
}
