package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.WishDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.repository.ProductRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    public Page<WishDto> selectWishes(Long userId,Pageable pageable) {

        Page<WishEntity> wishEntityList = wishRepository.findByUserId(userId, pageable);

        return wishEntityList.map(WishDto::toWishDto);
    }

    public List<WishDto> wishList(Long userId) {

        List<WishDto> wishDtoList = new ArrayList<>();

        List<WishEntity> wishEntityList = wishRepository.findAllWishList(userId);

        for(WishEntity wishEntity : wishEntityList){
            wishDtoList.add(WishDto.toWishDto(wishEntity));
        }

        return wishDtoList;
    }

    public void insertWish(Long userId, Long productId) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);

        ProductEntity productEntity =optionalProductEntity.get();

        WishEntity wishEntity = new WishEntity();

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(userId);

        MemberEntity memberEntity = optionalMemberEntity.get();

        wishEntity.setWishOrder(1);
        wishEntity.setMemberEntity(memberEntity);
        wishEntity.setProductEntity(productEntity);

        wishRepository.save(wishEntity);

    }


    public void deleteWish(Long id) {

        Optional<WishEntity> optionalWishEntity = wishRepository.findById(id);

        WishEntity wishEntity = optionalWishEntity.get();

        wishRepository.delete(wishEntity);

    }












}
