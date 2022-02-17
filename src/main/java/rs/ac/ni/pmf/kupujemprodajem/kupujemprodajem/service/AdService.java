package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.DiscountType;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.AdController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.AdNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.DuplicateResourceException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.CommentEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.AdMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.CommentMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.CommentRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    public EntityModel<AdDTO> getAd(@PathVariable final Long id) {
        final AdEntity adEntity = findAd(id);

        return ModelBuilder.buildAdModel(AdMapper.toDto(adEntity));
    }

    public CollectionModel<EntityModel<AdDTO>> getAllAds() {
        final List<EntityModel<AdDTO>> ads = adRepository.findAll().stream()
                .map(AdMapper::toDto)
                .map(ModelBuilder::buildAdModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                ads,
                linkTo(methodOn(AdController.class).getAllAds()).withSelfRel());
    }

    public CollectionModel<EntityModel<CommentDTO>> getAllAdComments(@PathVariable final Long id)
    {
        List<EntityModel<CommentDTO>> adComments = commentRepository.findAll().stream()
                .filter((x) -> adComments(x, id))
                .map(CommentMapper::toDto)
                .map(ModelBuilder::buildCommentModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                adComments,
                linkTo(methodOn(AdController.class).getAllAdComments(id)).withSelfRel()
        );
    }

    private boolean adComments(CommentEntity commentEntity, Long adID){
        if(commentEntity.getAd().getAdID() == adID){
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<EntityModel<AdDTO>> createAd(@RequestBody final AdDTO adDto)  {

        //adExists(adDto);

        final AdEntity adEntity = AdMapper.toEntity(adDto);
        adEntity.setStatus(AdStatus.Available);

        final AdEntity savedEntity = adRepository.save(adEntity);

        return ResponseEntity
                .created(linkTo(methodOn(AdController.class).getAd(savedEntity.getAdID())).toUri())
                .body(ModelBuilder.buildAdModel(AdMapper.toDto(savedEntity)));
    }

    public EntityModel<AdDTO> updateAd(@PathVariable Long id, @RequestBody AdDTO adDto) {
        final AdEntity entity = findAd(id);

        if(adDto.getQuantityAvailable() != null) entity.setQuantityAvailable(adDto.getQuantityAvailable());
        AdEntity savedEntity = adRepository.save(entity);
        return ModelBuilder.buildAdModel(AdMapper.toDto(savedEntity));
    }

    public ResponseEntity<?> updateAdPriceCurrency(@PathVariable Long id, @RequestBody AdDTO adDto) {
        final AdEntity entity = findAd(id);

        if(adDto.getPrice() != null) entity.setPrice(adDto.getPrice());
        if(adDto.getCurrency() != null) entity.setCurrency(adDto.getCurrency());
        AdEntity savedEntity = adRepository.save(entity);
        return ResponseEntity
                .accepted()
                .body(ModelBuilder.buildAdModel(AdMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> updateAdTitleDescription(@PathVariable Long id, @RequestBody AdDTO adDto) {
        final AdEntity entity = findAd(id);

        if(adDto.getTitle() != null) entity.setTitle(adDto.getTitle());
        if(adDto.getDescription() != null) entity.setDescription(adDto.getDescription());
        AdEntity savedEntity = adRepository.save(entity);
        return ResponseEntity
                .accepted()
                .body(ModelBuilder.buildAdModel(AdMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> deleteAd(@PathVariable Long id) {

        final AdEntity entity = findAd(id);

        adRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> changeAdStatus(@PathVariable final Long id, @RequestBody AdDTO adDTO)
    {
        AdEntity adEntity = findAd(id);

        if(adEntity.getStatus() == AdStatus.Available && (adDTO.getStatus() == AdStatus.OutOfStock || adDTO.getStatus() == AdStatus.Removed)){
            adDTO.setQuantityAvailable(0);
            adEntity.setStatus(adDTO.getStatus());
            return getResponseEntity(adEntity);
        } else if (adEntity.getStatus() == AdStatus.OutOfStock && (adDTO.getStatus() == AdStatus.Available || adDTO.getStatus() == AdStatus.Removed)){
            adEntity.setStatus(adDTO.getStatus());
            return getResponseEntity( adEntity);
        } else if (adEntity.getStatus() == AdStatus.Removed && (adDTO.getStatus() == AdStatus.Available || adDTO.getStatus() == AdStatus.OutOfStock)){
            adEntity.setStatus(adDTO.getStatus());
            return getResponseEntity(adEntity);
        } else {

            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(ApiError.builder()
                            .code(ApiError.ErrorCode.NOT_CHANGEABLE)
                            .message("Cannot change status of an ad with id '" + id + "' since it is in status '" + adEntity.getStatus() + "'")
                            .build());
        }
    }

    public ResponseEntity<?> outOfStockAd(@PathVariable final Long id){
        final AdEntity adEntity = findAd(id);

        if(adEntity.getStatus() == AdStatus.Available){
            adEntity.setStatus(AdStatus.OutOfStock);
            adEntity.setQuantityAvailable(0);
            return getResponseEntity(adEntity);
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                    .withTitle("Can't put ad to out of stock")
                .withDetail("Ad with an id '"+ id + "' can't be put to outOfStock cause it's in status " + adEntity.getStatus()));
    }

    public ResponseEntity<?> removeAd(@PathVariable final Long id){
        final AdEntity adEntity = findAd(id);

        if(adEntity.getStatus() == AdStatus.Available){
            adEntity.setStatus(AdStatus.Removed);
            adEntity.setQuantityAvailable(0);
            return getResponseEntity(adEntity);
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(ApiError.builder()
                        .code(ApiError.ErrorCode.NOT_REMOVABLE)
                        .message("Cannot change status of an ad with id '" + id + "' since it is in status '" + adEntity.getStatus() + "'")
                        .build());
    }

    private AdEntity findAd(final long id)
    {
        return adRepository.findById(id)
                .orElseThrow(()->new AdNotFoundException(id));
    }

    private void adExists(AdDTO adDTO){
        if (adDTO.getAdID() == adRepository.findById(adDTO.getAdID()).get().getAdID()){
            throw new DuplicateResourceException(ApiError.ResourceType.AD, "Ad with an id '"+ adDTO.getAdID() + "' already exists");
        }
    }
    private ResponseEntity<?> getResponseEntity(AdEntity adEntity) {
        final AdEntity completedAd = adRepository.save(adEntity);
        return ResponseEntity.ok(ModelBuilder.buildAdModel(AdMapper.toDto(completedAd)));
    }
}
