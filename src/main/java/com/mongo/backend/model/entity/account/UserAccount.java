package com.mongo.backend.model.entity.account;

import com.mongo.backend.model.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAccount {
    @Id
    private String uniqueId;
    private String userType;
    private String profileImage;
    private String Address;
    private BigInteger phoneNo;
    private boolean isActive;
    private List<Comments> userComments = List.of();
}
