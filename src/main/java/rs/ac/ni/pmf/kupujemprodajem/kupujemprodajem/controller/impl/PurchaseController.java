package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IPurchaseController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.PurchaseService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class PurchaseController implements IPurchaseController {

    private final PurchaseService _purchaseService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PurchaseDTO> getPurchase(final Long id){
        return _purchaseService.getPurchase(id);
    }

    @Override
    public CollectionModel<EntityModel<PurchaseDTO>> getAllPurchases() {
        return _purchaseService.getAllPurchases();
    }

    @Override
    public ResponseEntity<EntityModel<PurchaseDTO>> createPurchase(final PurchaseDTO purchaseDto){
        return _purchaseService.createPurchase(purchaseDto);
    }

    @Override
    public ResponseEntity<?> updatePurchase(final Long id,final PurchaseDTO purchaseDto){
        return _purchaseService.updatePurchase(id,purchaseDto);
    }

    @Override
    public ResponseEntity<?> deletePurchase(final Long id){
        return _purchaseService.deletePurchase(id);
    }
}
