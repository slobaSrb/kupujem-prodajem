package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.Builder;
import lombok.Data;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;

import java.time.OffsetDateTime;

@Data
@Builder
public class PurchaseDTO {
    private Long purchaseID;
    private Long adID;
    private Long buyerID;
    private Integer amountPurchesed;
    private OffsetDateTime datePurchased;
    private Double totalValueOfPurchase;
    private Currency currency;
}
