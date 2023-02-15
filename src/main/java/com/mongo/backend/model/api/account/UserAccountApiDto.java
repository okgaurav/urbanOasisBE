package com.mongo.backend.model.api.account;

import com.mongo.backend.model.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountApiDto {
    private String uniqueId;
    private String userType;
    private String profileImage;
    private String Address;
    private BigInteger phoneNo;
    private boolean isActive;

    private List<Comments> userComments;
}
