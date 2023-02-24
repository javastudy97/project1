package org.project.omwp.dto;

import lombok.*;
import org.project.omwp.entity.ReviewEntity;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    //리뷰 아이디
    private Long reviewId;
    //리뷰 내용
    private String reviewContent;
    //리뷰 작성시간
    private LocalDateTime reviewDate;
    //리뷰 작성자 회원번호
    private Long userId;
    //ProductEntity 의 product_id
    private Long productId;
    public static ReviewDto toReviewDto(ReviewEntity reviewEntity,
                                        Long productId){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(reviewEntity.getReviewId());
        reviewDto.setReviewContent(reviewEntity.getReviewContent());
        reviewDto.setReviewDate(reviewEntity.getReviewDate());
        reviewDto.setUserId(reviewEntity.getMemberEntity().getUserId()); // 작성자 -> 로그인 한사람
        reviewDto.setProductId(productId); // 해당 상품번호

        return reviewDto;
    }
}
