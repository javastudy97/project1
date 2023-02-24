package org.project.omwp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project.omwp.dto.OrderlistDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "orderlist")
public class OrderlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderlist_id")
    private Long orderlistId;

//  주문수량 (기본값 : 1)
    @Column(name = "orderlist_count", nullable = false)
    private int orderlistCount;

//////  주문가격
//    @Column(name = "orderlist_price", nullable = false)
//    private int orderlistPrice;

//  주문일
    @CreationTimestamp
    @Column(name = "orderlist_date", updatable = false)
    private LocalDateTime orderlistDate;

//  주문상태 : 1(주문완료) / 0(주문취소)
    @Column(name = "orderlist_status", nullable = false)
    private int orderlistStatus ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static OrderlistEntity toOrderlistEntity(OrderlistDto orderlistDto,
                                                    MemberEntity memberEntity,
                                                    ProductEntity productEntity){

        OrderlistEntity orderlistEntity = new OrderlistEntity();

        orderlistEntity.setOrderlistCount(1);
//        orderlistEntity.setOrderlistPrice(productEntity.getProductPrice());
        orderlistEntity.setOrderlistStatus(1);
        orderlistEntity.setMemberEntity(memberEntity);
        orderlistEntity.setProductEntity(productEntity);

        return orderlistEntity;
    }

}
