package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity;

import lombok.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Roles;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "full_name", nullable = true)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avg_rating", nullable = true)
    private Double avgRating;

    @Column(name = "salt", nullable = true)
    private String salt;

    //	@Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    private List<AdEntity> ads = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<RatingEntity> ratings = new ArrayList<>();

    //@Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCommenter")
    private List<CommentEntity> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "varchar(255)", nullable = false)
    private Roles role;

    //@Column(name = "role", columnDefinition = "varchar(255) default 'test'", nullable = false)
    //	@Enumerated(EnumType.ORDINAL)
    //	private Roles role;
}
