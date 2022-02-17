package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model;

import org.springframework.hateoas.EntityModel;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ModelBuilder {
    public static EntityModel<UserDTO> buildUserModel(final UserDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(UserController.class).getUser(dto.getUserID())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users")
        );
    }

    public static EntityModel<AdDTO> buildAdModel(final AdDTO dto) {

        final Long adID = dto.getAdID();
        EntityModel<AdDTO> adModel = EntityModel.of(dto,
                 linkTo(methodOn(AdController.class).getAd(adID)).withSelfRel(),
                 linkTo(methodOn(AdController.class).getAllAds()).withRel("all-ads"));

        if(dto.getStatus() == AdStatus.Available){
            adModel.add(linkTo(methodOn(AdController.class).outOfStockAd(adID)).withRel("out-of-stock-ad"));
            adModel.add(linkTo(methodOn(AdController.class).removeAd(adID)).withRel("remove-ad"));
        }

        return adModel;

    }

    public static EntityModel<CommentDTO> buildCommentModel(CommentDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CommentController.class).getComment(dto.getAdID())).withSelfRel(),
                linkTo(methodOn(CommentController.class).getAllComments()).withRel("all-comments")
        );
    }

    public static EntityModel<PurchaseDTO> buildPurchaseModel(PurchaseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PurchaseController.class).getPurchase(dto.getPurchaseID())).withSelfRel(),
                linkTo(methodOn(PurchaseController.class).getAllPurchases()).withRel("all-purchases")
        );
    }

    public static EntityModel<RatingDTO> buildRatingModel(RatingDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(RatingController.class).getRating(dto.getRatingID())).withSelfRel(),
                linkTo(methodOn(RatingController.class).getAllRatings()).withRel("all-ratings")

        );
    }
}
