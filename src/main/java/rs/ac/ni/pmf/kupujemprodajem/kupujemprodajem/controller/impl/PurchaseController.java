package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IPurchaseController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.PurchaseNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.PurchaseEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.PurchaseMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.PurchaseRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.PurchaseService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PurchaseController implements IPurchaseController {

    private PurchaseService _purchaseService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PurchaseDTO> getPurchase(@PathVariable final Long id){
        return _purchaseService.getPurchase(id);
    }

    @Override
    public CollectionModel<EntityModel<PurchaseDTO>> getAllPurchases() {
        return _purchaseService.getAllPurchases();
    }

    @Override
    public ResponseEntity<EntityModel<PurchaseDTO>> createPurchase(@RequestBody final PurchaseDTO purchaseDto){
        return _purchaseService.createPurchase(purchaseDto);
    }

    @Override
    public ResponseEntity<?> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDto){
        return _purchaseService.updatePurchase(id,purchaseDto);
    }

    @Override
    public ResponseEntity<?> deletePurchase(@PathVariable Long id){
        return _purchaseService.deletePurchase(id);
    }
}
