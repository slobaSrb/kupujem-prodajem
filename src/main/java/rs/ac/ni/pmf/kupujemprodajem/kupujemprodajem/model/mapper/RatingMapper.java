package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper;

import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.RatingEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

public class RatingMapper {
    public static RatingDTO toDto(final RatingEntity ratingEntity) {
        return RatingDTO.builder()
                .ratingID(ratingEntity.getRatingID())
                .rating(ratingEntity.getRating())
                .ratingComment(ratingEntity.getRatingComment())
                .adID(ratingEntity.getAd().getAdID())
                .userID(ratingEntity.getUser().getUserID())
               // .dateOfRatingPlacement(ratingEntity.getDateOfRating())
                .build();
    }

    public static RatingEntity toEntity(final RatingDTO ratingDto) {
        return RatingEntity.builder()
                .ratingID(ratingDto.getRatingID())
                .rating(ratingDto.getRating())
                .ratingComment(ratingDto.getRatingComment())
                .ad(AdEntity.builder().adID(ratingDto.getRatingID()).build())
                .user(UserEntity.builder().userID(ratingDto.getUserID()).build())
               // .dateOfRating(ratingDto.getDateOfRatingPlacement())
                .build();
    }
}
