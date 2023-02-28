package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.entity.OrderlistEntity;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.dto.OrderlistDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.ProductRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderlistService {

    private final OrderlistRepository orderlistRepository;
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public int addOrder(Long wishId) {
        WishEntity wishEntity = wishRepository.findById(wishId).get();
        OrderlistEntity orderlistEntity =
                OrderlistEntity.toOrderlistEntity(wishEntity.getMemberEntity(), wishEntity.getProductEntity());
        Long id = orderlistRepository.save(orderlistEntity).getOrderlistId();

        if (orderlistRepository.findById(id) == null) {
            return 0;
        }
        return 1;
    }



    @Transactional
    public OrderlistEntity insertOrder(Long userId, Long productId) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);

        ProductEntity productEntity =optionalProductEntity.get();

        OrderlistEntity orderlistEntity = new OrderlistEntity();

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(userId);

        MemberEntity memberEntity = optionalMemberEntity.get();

        orderlistEntity.setMemberEntity(memberEntity);
        orderlistEntity.setProductEntity(productEntity);
        orderlistEntity.setOrderlistCount(1);
        orderlistEntity.setOrderlistStatus(1);

        orderlistRepository.save(orderlistEntity);

        return orderlistEntity;
    }

    public List<OrderlistDto> selectAllOrder(Long userId) {

        List<OrderlistDto> orderlistDtoList = new ArrayList<>();

        List<OrderlistEntity> orderlistEntityList = orderlistRepository.findAllByuserId(userId);

        for(OrderlistEntity orderlistEntity : orderlistEntityList){

            orderlistDtoList.add(OrderlistDto.toOrderlistDto(orderlistEntity));

        }

        return orderlistDtoList;
    }

    @Transactional
    public void cancelOrder(Long id) {

        OrderlistDto orderlistDto = new OrderlistDto();

        Optional<OrderlistEntity> optionalOrderlistEntity = orderlistRepository.findById(id);

        OrderlistEntity orderlistEntity = optionalOrderlistEntity.get();

        orderlistEntity.setOrderlistStatus(0);

        orderlistRepository.save(orderlistEntity);

    }


    public List<OrderlistDto> selectAllCancel(Long userId) {

        List<OrderlistDto> orderlistDtoList = new ArrayList<>();

        List<OrderlistEntity> orderlistEntityList = orderlistRepository.findAllCancelByUserId(userId);

        for(OrderlistEntity orderlistEntity : orderlistEntityList){

            orderlistDtoList.add(OrderlistDto.toOrderlistDto(orderlistEntity));

        }

        return orderlistDtoList;
    }

    public void deleteCancelList(OrderlistDto orderlistDto) {

        Long id = orderlistDto.getOrderlistId();

        Optional<OrderlistEntity> optionalOrderlistEntity = orderlistRepository.findById(id);

        OrderlistEntity orderlistEntity = optionalOrderlistEntity.get();

        orderlistRepository.delete(orderlistEntity);

    }
}
