package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class CommentDTO {
    private Long commentID;
    private String comment;
    private OffsetDateTime commentDate;
    private Long userID;
    private Long mainCommentID;
    private Long adID;
}
