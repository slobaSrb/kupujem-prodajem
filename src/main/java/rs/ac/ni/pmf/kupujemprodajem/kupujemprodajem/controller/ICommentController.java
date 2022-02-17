package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;

@RequestMapping("/v1")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the comment"),
        @ApiResponse(responseCode = "201", description = "Comment has been created"),
        @ApiResponse(responseCode = "202", description = "Comment updated"),
        @ApiResponse(responseCode = "404", description = "Comment not found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "500", description = "Internal server error contact developer")
})
public interface ICommentController {

    @GetMapping("/comments")
    @Operation(summary = "Retrieve the list of all comments")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<CommentDTO>> getAllComments();

    @GetMapping("/comments/{id}")
    @Operation(summary = "Retrieve comment details")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<CommentDTO> getComment(@Parameter(description = "ID of a comment") @PathVariable Long id);

    // retrieve comments for specific ad

    @PostMapping("/comments")
    @Operation(summary = "Create a comment")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO);

    @PutMapping("/comments/{id}")
    @Operation(summary = "Updating a comment")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO);

    @DeleteMapping("/comments/{id}")
    @Operation(summary = "Deleting a comment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteComment(@PathVariable Long id);

}
