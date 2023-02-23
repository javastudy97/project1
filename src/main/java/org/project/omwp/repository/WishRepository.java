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
//    @Query(value = "select w.wish_id,w.user_id, p.product_id, p.product_type, p.product_name, p.product_price " +
//            "from wish w " +
//            "inner join member m on w.user_id=m.user_id " +
//            "inner join product p on w.product_id=p.product_id " +
//            "where w.user_id=:userId ",nativeQuery = true)
//    Page<WishEntity> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select * from wish w " +
            "inner join member m on w.user_id=m.user_id " +
            "inner join product p on w.product_id=p.product_id " +
            "where w.user_id=:userId",nativeQuery = true)
    List<WishEntity> findByUserId(@Param("userId") Long userId);
}
