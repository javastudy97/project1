package org.project.omwp.repository;

import org.project.omwp.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findByProductType(String productType);

    List<ProductEntity> findByProductNameContaining(String search);

    // 페이징 진행 중
//    @Query
//    Page<ProductEntity> findAllByType(Pageable pageable);
}
