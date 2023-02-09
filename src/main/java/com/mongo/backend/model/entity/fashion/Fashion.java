package com.mongo.backend.model.entity.fashion;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
public class Fashion {
    @Id
    private String uniqueId;
    private String productName;

//    private FashionCategory catCode;
//    private String pId;
//    private String tags;
//    private Double retailPrice;
//    private Double discountedPrice;
//    private Double discount;
//    private Double description;
//    private Double ratingCount;
//    private Double rating;
//    private String brandCode;
}
