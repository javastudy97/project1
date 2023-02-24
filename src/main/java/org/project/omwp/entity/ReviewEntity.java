package org.project.omwp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project.omwp.dto.ReviewDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    //  내용
    @Column(name = "review_content", nullable = false, length = 255)
    private String reviewContent;

    //  등록일
    @CreationTimestamp
    @Column(name = "review_date", updatable = false)
    private LocalDateTime reviewDate;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity memberEntity;

    // N:1
    // 댓글 작성시 product_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;


    public static ReviewEntity toInsertEntity(ReviewDto reviewDto,
                                              MemberEntity memberEntity,
                                              ProductEntity productEntity) {

        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setReviewContent(reviewDto.getReviewContent());
        reviewEntity.setProductEntity(productEntity);
        reviewEntity.setMemberEntity(memberEntity);

        return reviewEntity;
    }
}
