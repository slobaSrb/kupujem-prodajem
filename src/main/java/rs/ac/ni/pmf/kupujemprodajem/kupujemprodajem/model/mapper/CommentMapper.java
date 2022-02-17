package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper;

import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.CommentEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

public class CommentMapper {
    public static CommentDTO toDto(final CommentEntity commentEntity) {
        return CommentDTO.builder()
                .commentID(commentEntity.getCommentID())
                .comment(commentEntity.getComment())
                .commentDate(commentEntity.getCommentDate())
                .userID(commentEntity.getUserCommenter().getUserID())
                .mainCommentID(commentEntity.getCommentID())
                .adID(commentEntity.getAd().getAdID())
                .build();
    }

    public static CommentEntity toEntity(final CommentDTO commentDto) {
        return CommentEntity.builder()
                .commentID(commentDto.getCommentID())
                .comment(commentDto.getComment())
                .commentDate(commentDto.getCommentDate())
                .userCommenter(UserEntity.builder().userID(commentDto.getUserID()).build())
                .mainComment(CommentEntity.builder().commentID(commentDto.getCommentID()).build())
                .ad(AdEntity.builder().adID(commentDto.getAdID()).build())
                //.ad(commentDto.getAdID())
                .build();
    }
}
