package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity;

import lombok.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.AdStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Currency;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.DiscountType;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "ad_placement_date", nullable = false)
    private OffsetDateTime adPlacementDate;
//
//	@Column(name = "buyer_id", columnDefinition = "int default 'test'", nullable = true)
//	private Integer buyerID;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "avg_rating", nullable = true)
    private Integer avgRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AdStatus status;

    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity seller;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
    private List<CommentEntity> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
    private List<RatingEntity> ratings = new ArrayList<>();

}
