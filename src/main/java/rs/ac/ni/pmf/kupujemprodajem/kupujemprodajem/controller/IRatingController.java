package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;

@RequestMapping("/v1")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the resource"),
        @ApiResponse(responseCode = "201", description = "Resource has been created"),
        @ApiResponse(responseCode = "202", description = "Resource updated"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "500", description = "Internal server error contact developer")
})
public interface IRatingController {
    @GetMapping("/ratings")
    @Operation(summary = "Retrieve the list of all ratings")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<RatingDTO>> getAllRatings();

    @GetMapping("/ratings/{id}")
    @Operation(summary = "Retrieve rating details")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<RatingDTO> getRating(@Parameter(description = "ID of a rating") @PathVariable Long id);

    @PostMapping("/ratings")
    @Operation(summary = "Create a rating")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO);

    @PutMapping(value = "/ratings/{id}")
    @Operation(summary = "Updating a rating")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO);

    @DeleteMapping("/ratings/{id}")
    @Operation(summary = "Deleting a rating")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteRating(@PathVariable Long id);
}
