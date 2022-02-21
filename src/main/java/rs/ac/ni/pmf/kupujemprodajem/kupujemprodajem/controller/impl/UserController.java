package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.IUserController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

    private final UserService _userService;

    @Override
    public EntityModel<UserDTO> getUser(final Long id) {
        return _userService.getUser(id);
    }

    @Override
    public CollectionModel<EntityModel<UserDTO>> getAllUsers() {
        return _userService.getAllUsers();
    }

    @Override
    public CollectionModel<EntityModel<AdDTO>> getAllUserAds(final Long id) {
        return _userService.getAllUserAds(id);
    }

    @Override
    public CollectionModel<EntityModel<RatingDTO>> getAllUserRatings(final Long userID){
        return _userService.getAllUserRatings(userID);
    }

    @Override
    public CollectionModel<EntityModel<PurchaseDTO>> getAllUserPurchases(final Long userID){
        return _userService.getAllUserPurchases(userID);
    }

    public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody final UserDTO userDto) {
        return _userService.createUser(userDto);
    }

    @Override
    public ResponseEntity<?> updateUser(final Long id,@RequestBody final UserDTO userDTO) {
       return _userService.updateUser(id, userDTO);
    }

    @Override
    public ResponseEntity<?> deleteUser(final Long userID) {
        return _userService.deleteUser(userID);
    }

    @Override
    public ResponseEntity<?> findByUserName(final String userName){
        return _userService.findByUserName(userName);
    }

}
