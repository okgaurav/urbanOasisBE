package com.mongo.backend.mapper;

import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.model.entity.Comments;
import org.springframework.beans.BeanUtils;

public class CommentsMapper {
    public static CommentsApiDto toApi(Comments comments){
        CommentsApiDto commentsApiDto =new CommentsApiDto();
        BeanUtils.copyProperties(comments, commentsApiDto);
        return commentsApiDto;
    }

    public static Comments toEntity(CommentsApiDto commentsApiDto){
        Comments comments =new Comments();
        BeanUtils.copyProperties(commentsApiDto,comments);
        return comments;
    }
}
