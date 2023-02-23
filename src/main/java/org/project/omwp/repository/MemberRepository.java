package org.project.omwp.repository;

import org.project.omwp.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Page<MemberEntity> findByUserNameContaining(String keyword, Pageable pageable);

    Page<MemberEntity> findByUserEmailContaining(String keyword, Pageable pageable);
    
    Page<MemberEntity> findByUserRoleContaining(String keyword, Pageable pageable);

    Page<MemberEntity> findByUserId(Long userId, Pageable pageable);
}
