package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper;

import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.PurchaseDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.PurchaseEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;

public class PurchaseMapper {
    public static PurchaseDTO toDto(final PurchaseEntity purchaseEntity) {
        return PurchaseDTO.builder()
                .purchaseID(purchaseEntity.getPurchaseID())
                .adID(purchaseEntity.getAd().getAdID())
                .buyerID(purchaseEntity.getBuyer().getUserID())
                .amountPurchesed(purchaseEntity.getAmountPurchesed())
                .datePurchased(purchaseEntity.getDatePurchased())
                .totalValueOfPurchase(purchaseEntity.getTotalValueOfPurchase())
                .currency(purchaseEntity.getCurrency())
                .build();
    }

    public static PurchaseEntity toEntity(final PurchaseDTO purchaseDto) {
        return PurchaseEntity.builder()
                .purchaseID(purchaseDto.getPurchaseID())
                .ad(AdEntity.builder().adID(purchaseDto.getAdID()).build())
                .buyer(UserEntity.builder().userID(purchaseDto.getBuyerID()).build())
                .datePurchased(purchaseDto.getDatePurchased())
                .amountPurchesed(purchaseDto.getAmountPurchesed())
                .totalValueOfPurchase(purchaseDto.getTotalValueOfPurchase())
                .currency(purchaseDto.getCurrency())
                .build();
    }
}
