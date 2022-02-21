package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.PurchaseController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.BadRequestException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.PurchaseEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.PurchaseMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.PurchaseRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PurchaseDTO> getPurchase(final Long id){
        final PurchaseEntity purchaseEntity = findPurchase(id);
        return ModelBuilder.buildPurchaseModel(PurchaseMapper.toDto(purchaseEntity));
    }

    public CollectionModel<EntityModel<PurchaseDTO>> getAllPurchases() {

        final List<EntityModel<PurchaseDTO>> purchases = purchaseRepository.findAll().stream()
                .map(PurchaseMapper::toDto)
                .map(ModelBuilder::buildPurchaseModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                purchases,
                linkTo(methodOn(PurchaseController.class).getAllPurchases()).withSelfRel()
        );
    }

    public ResponseEntity<EntityModel<PurchaseDTO>> createPurchase(final PurchaseDTO purchaseDto){

        final PurchaseEntity savedEntity = purchaseRepository.save(PurchaseMapper.toEntity(purchaseDto));

        return ResponseEntity
                .created(linkTo(methodOn(PurchaseController.class).getPurchase(savedEntity.getPurchaseID())).toUri())
                .body(ModelBuilder.buildPurchaseModel(PurchaseMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> updatePurchase(final Long id,final PurchaseDTO purchaseDto){

        boolean changedForm = false;

        final PurchaseEntity entity = findPurchase(id);

        if(purchaseDto.getAmountPurchesed() != null) { changedForm = true; entity.setAmountPurchesed(purchaseDto.getAmountPurchesed()); }
        if(purchaseDto.getTotalValueOfPurchase() != null) { changedForm = true; entity.setTotalValueOfPurchase(purchaseDto.getTotalValueOfPurchase()); }
        if(purchaseDto.getDatePurchased() != null) { changedForm = true; entity.setDatePurchased(purchaseDto.getDatePurchased()); }

        if(!changedForm){
            throw new BadRequestException("Purchase data not valid.");
        }

        PurchaseEntity savedEntity = purchaseRepository.save(entity);
        return ResponseEntity
                .accepted()
                .body(ModelBuilder.buildPurchaseModel(PurchaseMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> deletePurchase(final Long id){

        final PurchaseEntity entity = findPurchase(id);

        purchaseRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }

    private PurchaseEntity findPurchase(final Long purchaseID) {
        return purchaseRepository.findById(purchaseID)
                .orElseThrow(()->new ResourceNotFoundException(ApiError.ResourceType.PURCHASE, "Purchase with id '" + purchaseID + "' not found"));
    }
}
