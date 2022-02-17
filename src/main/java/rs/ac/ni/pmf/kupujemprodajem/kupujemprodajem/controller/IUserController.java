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
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.UserDTO;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the resource"),
        @ApiResponse(responseCode = "201", description = "Resource has been created"),
        @ApiResponse(responseCode = "202", description = "Resource updated"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "500", description = "Internal server error contact developer")
})
public interface IUserController {
    @GetMapping("/users")
    @Operation(summary = "Retrieve the list of all users")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<UserDTO>> getAllUsers();

    @GetMapping("/users/{id}")
    @Operation(summary = "Retrieve user details")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<UserDTO> getUser(@Parameter(description = "ID of an user") @PathVariable Long id);

    @GetMapping("/users/{id}/ads")
    @Operation(summary = "Retrieve user's adds")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<AdDTO>> getAllUserAds(@PathVariable Long id);

    @GetMapping("/users/{userID}/ratings")
    @Operation(summary = "Retrieve user's ratings")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<RatingDTO>> getAllUserRatings(@PathVariable final Long userID);

    @GetMapping("/users/{userID}/purchases")
    @Operation(summary = "Retrieve user's purchases")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<PurchaseDTO>> getAllUserPurchases(@PathVariable final Long userID);

    @PostMapping("/users")
    @Operation(summary = "Create an user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createUser(@RequestBody UserDTO userDTO);

    @PutMapping("/users/{id}")
    @Operation(summary = "Updating an user")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Deleting an user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteUser(@PathVariable Long id);


}
