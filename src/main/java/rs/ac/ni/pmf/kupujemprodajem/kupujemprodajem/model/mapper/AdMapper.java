package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper;

import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.AdDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

public class AdMapper {

    public static AdDTO toDto(final AdEntity adEntity) {
        return AdDTO.builder()
                .adID(adEntity.getAdID())
                .title(adEntity.getTitle())
                .description(adEntity.getDescription())
                .price(adEntity.getPrice())
                .currency(adEntity.getCurrency())
                .adPlacementDate(adEntity.getAdPlacementDate())
                //.purchaseID(adEntity.getPurchase().getId())
                .category(adEntity.getCategory())
                .status(adEntity.getStatus())
                .quantityAvailable(adEntity.getQuantityAvailable())
                .sellerID(adEntity.getSeller().getUserID())
                //.amountPurchased(adEntity.getAmountPurchased())
                .build();
    }

    public static AdEntity toEntity(final AdDTO adDto) {
        return AdEntity.builder()
                .adID(adDto.getAdID())
                .title(adDto.getTitle())
                .description(adDto.getDescription())
                .price(adDto.getPrice())
                .currency(adDto.getCurrency())
                .adPlacementDate(adDto.getAdPlacementDate())
//				.purchase(Purchases.findPurchaseByUserID(adDto.getUserID()))
                .category(adDto.getCategory())
                //avarage rating mogu da ga izracunam posebnom metodom
                // za dobavljanje svih rejtinga pa podelim i to ubacim u entity
                // ako treba da sacuvam
                //.avgRating(adDto.getAvgRating())
                .status(adDto.getStatus())
                .quantityAvailable(adDto.getQuantityAvailable())
                .seller(UserEntity.builder().userID(adDto.getSellerID()).build())
                //.amountPurchased(adDto.getAmountPurchased())
                .build();
    }

}
