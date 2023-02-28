package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.WishDto;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public Page<WishDto> selectWishes(Long userId,Pageable pageable) {

        Page<WishEntity> wishEntityList = wishRepository.findByUserId(userId, pageable);
        return wishEntityList.map(WishDto::toWishDto);
    }
    @Transactional
    public int wishDelete(Long wishId) {
        WishEntity wishEntity = wishRepository.findById(wishId).get();
        if (wishEntity==null){
            return 0;
        } else {
            wishRepository.delete(wishEntity);
            return 1;
        }
    }
    public List<WishEntity> wishOrderUpdate(List<Long> wishIdList) {
        List<WishEntity> wishEntityList = new ArrayList<>();

        for (Long wishId : wishIdList){
            WishEntity wishEntity = wishRepository.findById(wishId).get();
            wishEntity.setWishOrder(1);
            wishEntityList.add(wishEntity);
        }
        return wishEntityList;
    }
}
