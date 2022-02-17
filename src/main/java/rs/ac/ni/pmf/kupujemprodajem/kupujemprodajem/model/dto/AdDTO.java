package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.DiscountType;

import java.time.OffsetDateTime;

@Data
@Builder
public class AdDTO {
    private Long adID;
    private String title;
    private String description;
    private Double price;
    private Currency currency;
    private OffsetDateTime adPlacementDate;
    private String category;
    private AdStatus status;
    private Integer quantityAvailable;
    private Long sellerID;
}
