package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.RatingController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.BadRequestException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.RatingEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.RatingMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.RatingRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepository ratingRepository;

    public EntityModel<RatingDTO> getRating(@PathVariable final Long id){
        return ModelBuilder.buildRatingModel(RatingMapper.toDto(findRating(id)));
    }

    private RatingEntity findRating(final Long ratingID) {
        return ratingRepository.findById(ratingID)
                .orElseThrow(()->new ResourceNotFoundException(ApiError.ResourceType.RATING, "Rating with id '" + ratingID + " not found."));
    }

    public CollectionModel<EntityModel<RatingDTO>> getAllRatings() {

        final List<EntityModel<RatingDTO>> ratings = ratingRepository.findAll().stream()
                .map(RatingMapper::toDto)
                .map(ModelBuilder::buildRatingModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                ratings,
                linkTo(methodOn(RatingController.class).getAllRatings()).withSelfRel()
        );
    }

    public ResponseEntity<EntityModel<RatingDTO>> createRating( final RatingDTO ratingDTO){

        final RatingEntity savedEntity = ratingRepository.save(RatingMapper.toEntity(ratingDTO));

        return ResponseEntity
                .created(linkTo(methodOn(RatingController.class).getRating(savedEntity.getRatingID())).toUri())
                .body(ModelBuilder.buildRatingModel(RatingMapper.toDto(savedEntity)));
    }

    public EntityModel<RatingDTO> updateRating(final Long id, final RatingDTO ratingDTO) {

        boolean changedForm = false;

        final RatingEntity entity = findRating(id);

        if(ratingDTO.getRatingComment() != null) { changedForm = true; entity.setRatingComment(ratingDTO.getRatingComment()); }
        if(ratingDTO.getRating() != null) { changedForm = true; entity.setRating(ratingDTO.getRating()); }


        if(!changedForm){
            throw new BadRequestException("Rating not valid.");
        }


        RatingEntity savedEntity = ratingRepository.save(entity);
        return ModelBuilder.buildRatingModel(RatingMapper.toDto(savedEntity));
    }


    public ResponseEntity<?> deleteRating(Long ratingID){

        final RatingEntity entity = findRating(ratingID);

        ratingRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }
}
