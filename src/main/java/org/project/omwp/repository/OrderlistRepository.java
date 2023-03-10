package org.project.omwp.repository;

import org.project.omwp.entity.OrderlistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select * from orderlist o " +
            "where o.user_id=:userId", nativeQuery = true)
    Page<OrderlistEntity> findAllByuserId2(Long userId, Pageable pageable);

    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where o.product_id=:productId", nativeQuery = true)
    Page<OrderlistEntity> findAllByProductId(@Param("productId") String keyword, Pageable pageable);

    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where p.product_name like %:productName%", nativeQuery = true)
    Page<OrderlistEntity> findByProductNameContaining(@Param("productName") String keyword, Pageable pageable);

    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where m.user_name like %:userName%", nativeQuery = true)
    Page<OrderlistEntity> findByUserNameContaining(@Param("userName") String keyword, Pageable pageable);

    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where m.user_email like %:userEmail%", nativeQuery = true)
    Page<OrderlistEntity> findByUserEmailContaining(@Param("userEmail") String keyword, Pageable pageable);

    @Query(value = "select * from orderlist ", nativeQuery = true)
    Page<OrderlistEntity> findAllOrders(Pageable pageable);

    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where o.orderlist_id=:orderlistId", nativeQuery = true)
    Page<OrderlistEntity> findAllByOrderlistId(@Param("orderlistId") Long orderlistId, Pageable pageable);

    // 관리자 페이지 검색기능

    //주문번호 검색
    @Query(value = "select * from orderlist where orderlist_id =:id ", nativeQuery = true )
    Page<OrderlistEntity> searchRecodeNumber(@Param("id")Long orderlistId, Pageable pageable);

    //상품번호 검색
    @Query(value = "select * from orderlist where product_id =:id ", nativeQuery = true)
    Page<OrderlistEntity> searchRecodeNumber2(@Param("id")Long productId, Pageable pageable);

    //상품이름 검색
    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "inner join product p on o.product_id=p.product_id " +
            "where p.product_name like %:productName% ", nativeQuery = true)
    Page<OrderlistEntity> searchRecodeProductName(@Param("productName") String keyword, Pageable pageable);

    //회원이름 검색
    @Query(value = "select * from orderlist o " +
            "inner join member m on o.user_id=m.user_id " +
            "where m.user_name like %:userName% ", nativeQuery = true)
    Page<OrderlistEntity> searchRecode1(@Param("userName") String keyword, Pageable pageable);

    //회원이메일 검색
    @Query(value = "select * from orderlist o "+
            "inner join member m on o.user_id=m.user_id " +
            "where m.user_email like %:userEmail% ", nativeQuery = true)
    Page<OrderlistEntity> searchRecode2(@Param("userEmail") String keyword, Pageable pageable);
}
