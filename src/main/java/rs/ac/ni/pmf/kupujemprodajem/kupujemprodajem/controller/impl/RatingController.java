package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;





import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IRatingController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.RatingService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class RatingController implements IRatingController {

    private final RatingService _ratingService;

    @Override
    public EntityModel<RatingDTO> getRating(final Long id){
        return _ratingService.getRating(id);
    }

    @Override
    public CollectionModel<EntityModel<RatingDTO>> getAllRatings() {
        return _ratingService.getAllRatings();
    }

    @Override
    public ResponseEntity<EntityModel<RatingDTO>> createRating(@RequestBody final RatingDTO ratingDTO){
        return _ratingService.createRating(ratingDTO);
    }

    @Override
    public String updateRating(final Long id,@RequestBody final String ratingDTO){
        //return _ratingService.updateRating(id,ratingDTO);
        return ratingDTO;
    }

    @Override
    public ResponseEntity<?> deleteRating(final Long id){
        return _ratingService.deleteRating(id);
    }
}
