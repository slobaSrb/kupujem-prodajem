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
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;

@RequestMapping("/v1")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the purchas"),
        @ApiResponse(responseCode = "201", description = "Purchase has been created"),
        @ApiResponse(responseCode = "202", description = "Purchase updated"),
        @ApiResponse(responseCode = "404", description = "Purchase not found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "500", description = "Internal server error contact developer")
})
public interface IPurchaseController {

    @GetMapping("/purchases")
    @Operation(summary = "Retrieve the list of all purchases")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<PurchaseDTO>> getAllPurchases();

    @GetMapping("/purchases/{id}")
    @Operation(summary = "Retrieve purchase details")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<PurchaseDTO> getPurchase(@Parameter(description = "ID of a purchase") @PathVariable Long id);

    @PostMapping("/purchases")
    @Operation(summary = "Create a purchase")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createPurchase(@RequestBody PurchaseDTO purchaseDTO);

    @PutMapping("/purchases/{id}")
    @Operation(summary = "Updating a purchase")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO);

    @DeleteMapping("/purchases/{id}")
    @Operation(summary = "Deleting a purchase")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deletePurchase(@PathVariable Long id);
}
