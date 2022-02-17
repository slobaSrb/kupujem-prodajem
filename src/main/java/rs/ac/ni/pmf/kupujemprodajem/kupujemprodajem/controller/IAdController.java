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
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;


@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the ad"),
        @ApiResponse(responseCode = "201", description = "Ad has been created"),
        @ApiResponse(responseCode = "202", description = "Ad updated"),
        @ApiResponse(responseCode = "404", description = "Ad not found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "500", description = "Internal server error contact developer")
})
public interface IAdController {

    @GetMapping("/ads")
    @Operation(summary = "Retrieve the list of all ads")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<AdDTO>> getAllAds();

    @GetMapping("/ads/{id}")
    @Operation(summary = "Retrieve ad details")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<AdDTO> getAd(@Parameter(description = "ID of an ad") @PathVariable Long id);

    @GetMapping("/ads/{adID}/comments")
    @Operation(summary = "Retrieve ad's comments")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<CommentDTO>> getAllAdComments(@PathVariable final Long id);

    @PostMapping("/ads")
    @Operation(summary = "Create an ad")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createAd(@RequestBody AdDTO adDTO);

    @PutMapping("/ads/{id}")
    @Operation(summary = "Update ad quantity")
    @ResponseStatus(HttpStatus.ACCEPTED)
    EntityModel<AdDTO> updateAd(@PathVariable Long id, @RequestBody AdDTO adDTO);

    @PutMapping("/ads/{id}/updatePriceCurrency")
    @Operation(summary = "Update price and/or currency of an ad")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<?> updateAdPriceCurrency(@PathVariable Long id, @RequestBody AdDTO adDto);

    @PutMapping("/ads/{id}/updateTitleDescription")
    @Operation(summary = "Update title and/or description of an ad")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<?> updateAdTitleDescription(@PathVariable Long id, @RequestBody AdDTO adDto);

    @DeleteMapping("/ads/{id}")
    @Operation(summary = "Deleting an ad")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteAd(@PathVariable Long id);

    @PutMapping("/ads/{id}/changeStatus")
    @Operation(summary = "Changing status of an ad")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> changeAdStatus(@PathVariable Long id, @RequestBody AdDTO adDTO);

    @PutMapping("/ads/{id}/outOfStockAd")
    @Operation(summary = "Make ad out of stock")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> outOfStockAd(@PathVariable Long id);

    @PutMapping("/ads/{id}/removeAd")
    @Operation(summary = "Make ad out of stock")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> removeAd(@PathVariable Long id);

}