package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.WishDto;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

//    public List<WishDto> selectWishes(Long userId) {
//        List<WishDto> wishDtoList = new ArrayList<>();
//
//        List<WishEntity> wishEntityList = wishRepository.findByUserId(userId);
//
//        for(WishEntity wishEntity : wishEntityList) {
//            wishDtoList.add(WishDto.toWishDto(wishEntity));
//        }
//
//        return wishDtoList;
//    }
}
