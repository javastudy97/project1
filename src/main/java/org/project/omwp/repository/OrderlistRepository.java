package org.project.omwp.repository;

import org.project.omwp.entity.OrderlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlistRepository extends JpaRepository<OrderlistEntity, Long> {
}
