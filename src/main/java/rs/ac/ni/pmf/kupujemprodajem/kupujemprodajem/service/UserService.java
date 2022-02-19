package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.UserController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.BadRequestException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.RatingDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.UserDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.PurchaseEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.RatingEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.AdMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.PurchaseMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.RatingMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.UserMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.AdRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.PurchaseRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.RatingRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Data
@Service
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final RatingRepository ratingRepository;
    private final PurchaseRepository purchaseRepository;

    public EntityModel<UserDTO> getUser(final Long id) {
        final UserEntity userEntity = findUser(id);

        return ModelBuilder.buildUserModel(UserMapper.toDto(userEntity));
    }

    public CollectionModel<EntityModel<UserDTO>> getAllUsers() {
        // TODO Auto-generated method stub
        List<EntityModel<UserDTO>> users = userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .map(ModelBuilder::buildUserModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel()
        );
    }

    public CollectionModel<EntityModel<AdDTO>> getAllUserAds(final Long id) {
        List<EntityModel<AdDTO>> userAds = adRepository.findAll().stream()
                .filter((x) -> userAds(x,id))
                .map(AdMapper::toDto)
                .map(ModelBuilder::buildAdModel)
                .collect(Collectors.toList());


        return CollectionModel.of(
                userAds,
                linkTo(methodOn(UserController.class).getAllUserAds(id)).withSelfRel()
        );
    }
    private boolean userAds(final AdEntity adEntity, final Long userID){
        if(adEntity.getSeller().getUserID().equals(userID)){
            return true;
        } else {
            return false;
        }
    }

    public CollectionModel<EntityModel<RatingDTO>> getAllUserRatings(final Long userID){
        List<EntityModel<RatingDTO>> userRatings = ratingRepository.findAll().stream()
                .filter((x) -> userRatings(x,userID))
                .map(RatingMapper::toDto)
                .map(ModelBuilder::buildRatingModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                userRatings,
                linkTo(methodOn(UserController.class).getAllUserRatings(userID)).withSelfRel()
        );
    }

    private boolean userRatings(final RatingEntity ratingEntity, final Long userID){
        if(ratingEntity.getUser().getUserID().equals(userID)){
            return true;
        } else {
            return false;
        }
    }

    public CollectionModel<EntityModel<PurchaseDTO>> getAllUserPurchases(final Long userID){
        List<EntityModel<PurchaseDTO>> userPurchases = purchaseRepository.findAll().stream()
                .filter((x) -> userPurchases(x, userID))
                .map(PurchaseMapper::toDto)
                .map(ModelBuilder::buildPurchaseModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                userPurchases,
                linkTo(methodOn(UserController.class).getAllUserPurchases(userID)).withSelfRel()
        );
    }

    private boolean userPurchases(final PurchaseEntity purchaseEntity, final Long userID){
        if(purchaseEntity.getBuyer().getUserID() == userID){
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<EntityModel<UserDTO>> createUser(final UserDTO userDto) {
        final UserEntity userEntity = UserMapper.toEntity(userDto);

        final UserEntity savedEntity = userRepository.save(userEntity);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(savedEntity.getUserID())).toUri())
                .body(ModelBuilder.buildUserModel(UserMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> updateUser(final Long userID, final UserDTO userDTO) {

        boolean changedForm = false;

        final UserEntity entity = findUser(userID);

        if(userDTO.getUserName() != null && notInUserNames(userDTO.getUserName())){ changedForm = true; entity.setUserName(userDTO.getUserName());}
        if(userDTO.getFullName() != null){  changedForm = true; entity.setFullName(userDTO.getFullName());}
        if(userDTO.getEmail() != null) { changedForm = true; entity.setEmail(userDTO.getEmail());}
        if(userDTO.getRole() != null) { changedForm = true; entity.setRole(userDTO.getRole());}

        if(!changedForm){
            throw new BadRequestException("Data for updating the user not valid.");
        }

        UserEntity savedEntity = userRepository.save(entity);

        return ResponseEntity
                .accepted()
                .body(ModelBuilder.buildUserModel(UserMapper.toDto(savedEntity)));

    }

    public ResponseEntity<?> deleteUser(final Long userID) {
        final UserEntity entity = findUser(userID);

        userRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }

    private UserEntity findUser(final Long userID){
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException(ApiError.ResourceType.USER, "User with id '" + userID + "' not found."));
    }

    private boolean notInUserNames(final String userName)
    {
        for (UserEntity entity : userRepository.findAll()){
            if(entity.getUserName().equals(userName)){
                return false;
            }
        }
        return true;
    }

    public ResponseEntity<?> findByUserName(final String userName){

        if(userName == null || userName.isEmpty() && userName.length() < 6){
            throw new BadRequestException("Username not valid.");
        }

        UserDTO dto = userRepository.findByUserName(userName)
                .map(UserMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(ApiError.ResourceType.USER, "User not found"));

        return ResponseEntity
                .ok()
                .body(ModelBuilder.buildUserModel(dto));
    }
}
