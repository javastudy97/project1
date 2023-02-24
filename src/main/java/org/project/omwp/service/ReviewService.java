package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.ReviewDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.entity.ReviewEntity;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.ProductRepository;
import org.project.omwp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Long insertReviewDo(ReviewDto reviewDto){
        // member 테이블의 작성자 id가 있는지 확인
        Optional<MemberEntity> optionalReviewEntity1
                =memberRepository.findById(reviewDto.getUserId());
        // product 테이블의 상품번호가 있는지 확인
        Optional<ProductEntity> optionalReviewEntity2
                = productRepository.findById(reviewDto.getProductId());
        if (optionalReviewEntity1.isPresent()&&optionalReviewEntity2.isPresent()) {

            MemberEntity memberEntity = optionalReviewEntity1.get();
            ProductEntity productEntity = optionalReviewEntity2.get();

            ReviewEntity reviewEntity =
                    ReviewEntity.toInsertEntity(reviewDto, memberEntity, productEntity);

            return reviewRepository.save(reviewEntity).getReviewId();
        }else {
            return null;
        }
    }

    public List<ReviewDto> reviewDtoListDo(Long productId) {

        ProductEntity productEntity = productRepository.findById(productId).get();

        List<ReviewEntity> reviewEntityList =
                reviewRepository.findAllByProductEntityOrderByReviewIdDesc(productEntity);

        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (ReviewEntity reviewEntity : reviewEntityList){
            ReviewDto reviewDto = ReviewDto.toReviewDto(reviewEntity, productId);
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
}
