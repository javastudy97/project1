package org.project.omwp.repository;

import org.project.omwp.entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderlistRepository extends JpaRepository<OrderlistEntity, Long> {

    @Query(value = "select o.* from member m inner join orderlist o on m.user_id=o.user_id where o.user_id=:userId and orderlist_status=1 order by orderlist_date desc ", nativeQuery = true)
    List<OrderlistEntity> findAllByuserId(@Param("userId") Long userId);

    @Query(value = "select o.* from member m inner join orderlist o on m.user_id=o.user_id where o.user_id=:userId and orderlist_status=0 order by orderlist_date desc ", nativeQuery = true)
    List<OrderlistEntity> findAllCancelByUserId(@Param("userId") Long userId);
}
