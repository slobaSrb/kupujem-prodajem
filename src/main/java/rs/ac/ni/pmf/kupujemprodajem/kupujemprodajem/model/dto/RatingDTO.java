package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class RatingDTO {
    private Long ratingID;
    private Integer rating;
    private String ratingComment;
    private Long adID;
    private Long userID;
    private OffsetDateTime dateOfRatingPlacement;
}
