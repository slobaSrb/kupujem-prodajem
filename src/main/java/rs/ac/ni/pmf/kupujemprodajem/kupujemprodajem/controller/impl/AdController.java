package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IAdController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.AdNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.CommentEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.AdMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.CommentMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.CommentRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.AdService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AdController implements IAdController {

    private final AdService _adService;

    @Override
    public EntityModel<AdDTO> getAd(@PathVariable final Long id) {
        return _adService.getAd(id);
    }

    @Override
    public CollectionModel<EntityModel<AdDTO>> getAllAds() {
        return _adService.getAllAds();
    }

    // all comments for an ad
    @Override
    public CollectionModel<EntityModel<CommentDTO>> getAllAdComments(@PathVariable final Long id)
    {
        return _adService.getAllAdComments(id);
    }

    @Override
    public ResponseEntity<EntityModel<AdDTO>> createAd(@RequestBody final AdDTO adDto)  {
        return _adService.createAd(adDto);
    }

    @Override
    public EntityModel<AdDTO> updateAd(@PathVariable Long id, @RequestBody AdDTO adDto) {
        return _adService.updateAd(id, adDto);
    }

    @Override
    public ResponseEntity<?> updateAdPriceCurrency(@PathVariable Long id, @RequestBody AdDTO adDto) {
       return _adService.updateAdPriceCurrency(id, adDto);
    }

    @Override
    public ResponseEntity<?> updateAdTitleDescription(@PathVariable Long id, @RequestBody AdDTO adDto) {
        return _adService.updateAdTitleDescription(id, adDto);
    }

    @Override
    public ResponseEntity<?> deleteAd(@PathVariable Long id) {

        return _adService.deleteAd(id);
    }

    @Override
    public ResponseEntity<?> changeAdStatus(@PathVariable final Long id, @RequestBody AdDTO adDTO)
    {
        return _adService.changeAdStatus(id, adDTO);
    }

    @Override
    public ResponseEntity<?> outOfStockAd(@PathVariable final Long id)
    {
        return _adService.outOfStockAd(id);
    }

    @Override
    public ResponseEntity<?> removeAd(@PathVariable final Long id)
    {
        return _adService.removeAd(id);
    }
}
