package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.RatingController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.RatingNotFoundException;
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
public class RatingService {
    private final RatingRepository ratingRepository;

    public EntityModel<RatingDTO> getRating(@PathVariable final Long id){
        return ModelBuilder.buildRatingModel(RatingMapper.toDto(findRating(id)));
    }

    private RatingEntity findRating(Long ratingID) {
        return ratingRepository.findById(ratingID)
                .orElseThrow(()->new RatingNotFoundException(ratingID));
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

    public ResponseEntity<EntityModel<RatingDTO>> createRating(@RequestBody final RatingDTO ratingDto){

        final RatingEntity savedEntity = ratingRepository.save(RatingMapper.toEntity(ratingDto));

        return ResponseEntity
                .created(linkTo(methodOn(RatingController.class).getRating(savedEntity.getRatingID())).toUri())
                .body(ModelBuilder.buildRatingModel(RatingMapper.toDto(savedEntity)));
    }

    public EntityModel<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        final RatingEntity entity = findRating(id);

        if(ratingDTO.getRatingComment() != null) entity.setRatingComment(ratingDTO.getRatingComment());
        if(ratingDTO.getRating() != null) entity.setRating(ratingDTO.getRating());

        RatingEntity savedEntity = ratingRepository.save(entity);
        return ModelBuilder.buildRatingModel(RatingMapper.toDto(savedEntity));
    }


    public ResponseEntity<?> deleteRating(@PathVariable Long ratingID){

        final RatingEntity entity = findRating(ratingID);

        ratingRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }
}
