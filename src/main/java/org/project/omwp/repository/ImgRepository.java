package org.project.omwp.repository;

import org.project.omwp.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImgRepository extends JpaRepository<ImgEntity, Long> {



    ImgEntity findByImgNewName(String imgNewName);


    @Query(value = "select i1.* from img i1  inner join product p  on i1.product_id=p.product_id where i1.product_id=:product_id",
    nativeQuery = true)
    Optional<ImgEntity> findByImageNewNameOnProject(@Param("product_id") Long product_id);
}
