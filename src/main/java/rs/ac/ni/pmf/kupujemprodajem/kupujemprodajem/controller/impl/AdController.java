package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IAdController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.AdService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class AdController implements IAdController {

    private final AdService _adService;

    @Override
    public EntityModel<AdDTO> getAd(final Long id) {
        return _adService.getAd(id);
    }

    @Override
    public CollectionModel<EntityModel<AdDTO>> getAllAds() {
        return _adService.getAllAds();
    }

    // all comments for an ad
    @Override
    public CollectionModel<EntityModel<CommentDTO>> getAllAdComments(final Long id)
    {
        return _adService.getAllAdComments(id);
    }

    @Override
    public ResponseEntity<EntityModel<AdDTO>> createAd(final AdDTO adDto)  {
        return _adService.createAd(adDto);
    }

    @Override
    public EntityModel<AdDTO> updateAd(final Long id, final AdDTO adDTO) {
        return _adService.updateAd(id, adDTO);
    }

    @Override
    public ResponseEntity<?> updateAdPriceCurrency(final Long id, final AdDTO adDto) {
       return _adService.updateAdPriceCurrency(id, adDto);
    }

    @Override
    public ResponseEntity<?> updateAdTitleDescription(final Long id, final AdDTO adDto) {
        return _adService.updateAdTitleDescription(id, adDto);
    }

    @Override
    public ResponseEntity<?> deleteAd(final Long id) {

        return _adService.deleteAd(id);
    }

    @Override
    public ResponseEntity<?> changeAdStatus(final Long id, final AdDTO adDTO)
    {
        return _adService.changeAdStatus(id, adDTO);
    }

    @Override
    public ResponseEntity<?> outOfStockAd(final Long id)
    {
        return _adService.outOfStockAd(id);
    }

    @Override
    public ResponseEntity<?> removeAd(final Long id)
    {
        return _adService.removeAd(id);
    }
}
