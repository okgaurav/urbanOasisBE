package com.mongo.backend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusApiDto {
    private String uniqueId;
    private Boolean isVisible;
}
