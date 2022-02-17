package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;





import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IRatingController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.RatingNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.RatingEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.AdMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.RatingMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.RatingRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.RatingService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class RatingController implements IRatingController {

    private RatingService _ratingService;

    @Override
    public EntityModel<RatingDTO> getRating(@PathVariable final Long id){
        return _ratingService.getRating(id);
    }

    @Override
    public CollectionModel<EntityModel<RatingDTO>> getAllRatings() {
        return _ratingService.getAllRatings();
    }

    @Override
    public ResponseEntity<EntityModel<RatingDTO>> createRating(@RequestBody final RatingDTO ratingDto){
        return _ratingService.createRating(ratingDto);
    }

    @Override
    public EntityModel<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        return _ratingService.updateRating(id,ratingDTO);
    }


    @Override
    public ResponseEntity<?> deleteRating(@PathVariable Long id){
        return _ratingService.deleteRating(id);
    }
}
