package org.project.omwp.repository;

import org.project.omwp.entity.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Long> {
    @Query(value = "select * from wish w " +
            "inner join member m on w.user_id=m.user_id " +
            "inner join product p on w.product_id=p.product_id " +
            "where w.user_id=:userId ",nativeQuery = true)
    Page<WishEntity> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "select w.* from member m inner join wish w on m.user_id=w.user_id where w.user_id=:userId and wish_order=1 ", nativeQuery = true)
    List<WishEntity> findAllWishList(@Param("userId") Long userId);

    // 위시리스트 페이징
    Page<WishEntity> findAll(Pageable pageable);
}
