package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IUserController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.UserNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.*;
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
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

    private UserService _userService;

    @Override
    public EntityModel<UserDTO> getUser(@PathVariable final Long id) {
        return _userService.getUser(id);
    }

    @Override
    public CollectionModel<EntityModel<UserDTO>> getAllUsers() {
        return _userService.getAllUsers();
    }

    // retrieve all ads for the user
    @Override
    public CollectionModel<EntityModel<AdDTO>> getAllUserAds(@PathVariable final Long id) {
        return _userService.getAllUserAds(id);
    }

    // retrieve all ratings for the user

    @Override
    public CollectionModel<EntityModel<RatingDTO>> getAllUserRatings(@PathVariable final Long userID){
        return _userService.getAllUserRatings(userID);
    }

    // retrieve all purchases of the user
    @Override
    public CollectionModel<EntityModel<PurchaseDTO>> getAllUserPurchases(@PathVariable final Long userID){
        return _userService.getAllUserPurchases(userID);
    }

    @Override
    public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody UserDTO userDto) {
        return _userService.createUser(userDto);
    }

    @Override
    public ResponseEntity<?> updateUser(@PathVariable final Long userID, @RequestBody UserDTO userDto) {
       return _userService.updateUser(userID, userDto);
    }

    @Override
    public ResponseEntity<?> deleteUser(@PathVariable Long userID) {
        return _userService.deleteUser(userID);
    }

}
