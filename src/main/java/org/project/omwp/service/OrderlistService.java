package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.entity.OrderlistEntity;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderlistService {

    private final OrderlistRepository orderlistRepository;
    private final WishRepository wishRepository;

    public int addOrder(Long wishId) {
        WishEntity wishEntity = wishRepository.findById(wishId).get();
        OrderlistEntity orderlistEntity =
        OrderlistEntity.toOrderlistEntity(wishEntity.getMemberEntity(),wishEntity.getProductEntity());
        Long id = orderlistRepository.save(orderlistEntity).getOrderlistId();

        if(orderlistRepository.findById(id)==null){
            return 0;
        }
        return 1;
    }
}
