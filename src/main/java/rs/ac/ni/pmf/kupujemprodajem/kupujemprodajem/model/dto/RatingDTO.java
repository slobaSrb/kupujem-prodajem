package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingDTO {
    private Long ratingID;
    private Integer rating;
    private String ratingComment;
    private Long adID;
    private Long userID;
}
