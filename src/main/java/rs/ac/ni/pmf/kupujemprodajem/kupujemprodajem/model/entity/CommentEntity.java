package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "comments")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "comment_date", nullable = false)
    private OffsetDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity userCommenter;

    @ManyToOne
    private CommentEntity mainComment;

    @ManyToOne
    @JoinColumn(name = "adID")
    private AdEntity ad;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mainComment")
    private List<CommentEntity> allSubComments = new ArrayList<>();
}
