package com.mongo.backend.model.api.fashion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FashionApiDto {
    @Id
    private String uniqueId;
    private String productName;
    private String category;
    private String pId;
    private List<String> tags;
    private Double retailPrice;
    private Double discountedPrice;
    private Double discount;
    private String description;
    private Double ratingCount;
    private Double rating;
    private String brand;
    private Integer units;
    private Boolean isVisible;
}
