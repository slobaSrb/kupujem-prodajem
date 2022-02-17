package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ratings")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingID;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "rating_comment", nullable = true)
    private String ratingComment;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "adID")
    private AdEntity ad;

    @Column(name = "date_of_rating", nullable = false)
    private OffsetDateTime dateOfRating;
}
