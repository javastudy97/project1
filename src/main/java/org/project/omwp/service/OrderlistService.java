package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.entity.OrderlistEntity;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.dto.OrderlistDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.ProductRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<OrderlistDto> selectAllOrder2(Long userId, Pageable pageable) {
        Page<OrderlistEntity> orderlistEntities =
                orderlistRepository.findAllByuserId2(userId,pageable);

        return orderlistEntities.map(OrderlistDto::toOrderlistDto);
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


    public Page<OrderlistDto> searchOrderlistDo(Long orderlistId, Pageable pageable) {
        Page<OrderlistEntity> orderlistEntity =
                orderlistRepository.findAllByOrderlistId(orderlistId,pageable);

        return orderlistEntity.map(OrderlistDto::orderlistDto);
    }

//    주문내역 조회
    public Page<OrderlistDto> searchListDo(String type, String keyword, Pageable pageable) {
        if(type.equals("productId")) {
            Page<OrderlistEntity> orderlistEntity =
                    orderlistRepository.findAllByProductId(keyword,pageable);
            return orderlistEntity.map(OrderlistDto::orderlistDto);
        } else if(type.equals("productName")) {
            Page<OrderlistEntity> orderlistEntity =
                    orderlistRepository.findByProductNameContaining(keyword,pageable);
            return orderlistEntity.map(OrderlistDto::orderlistDto);
        } else if(type.equals("userName")) {
            Page<OrderlistEntity> orderlistEntity =
                    orderlistRepository.findByUserNameContaining(keyword,pageable);
            return orderlistEntity.map(OrderlistDto::orderlistDto);
        } else if(type.equals("email")) {
            Page<OrderlistEntity> orderlistEntity =
                    orderlistRepository.findByUserEmailContaining(keyword,pageable);
            return orderlistEntity.map(OrderlistDto::orderlistDto);
        }

        return null;
    }

    public Page<OrderlistDto> selectOrderlist(Pageable pageable) {
        Page<OrderlistEntity> orderlistEntity =
                orderlistRepository.findAllOrders(pageable);

        return orderlistEntity.map(OrderlistDto::orderlistDto);
    }
}
