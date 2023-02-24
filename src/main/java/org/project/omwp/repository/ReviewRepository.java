package org.project.omwp.repository;

import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByProductEntityOrderByReviewIdDesc(ProductEntity productEntity);

    //    List<ReviewEntity> findAllByProductEntity(ProductEntity productEntity);
//
//    List<ReviewEntity> findAllByProductEntityOrderByIdDesc(ProductEntity productEntity);
//
    void findAllByProductEntityProductId(ProductEntity productEntity);
    void findAllByMemberEntityUserId(MemberEntity memberEntity);
}
