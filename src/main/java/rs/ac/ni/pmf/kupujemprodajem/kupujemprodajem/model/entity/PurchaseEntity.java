package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity;

import lombok.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "purchases")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseID;

    @ManyToOne
    @JoinColumn(name = "adID")
    private AdEntity ad;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity buyer;

    @Column(name = "date_purchased", nullable = false)
    private OffsetDateTime datePurchased;

    @Column(name = "amount_purchesed", nullable = false)
    private Integer amountPurchesed;

    @Column(name = "total_value_of_purchase", nullable = false)
    private Double totalValueOfPurchase;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;
}
