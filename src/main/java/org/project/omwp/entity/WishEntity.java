package org.project.omwp.entity;

import lombok.*;
import org.project.omwp.dto.WishDto;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "wish")
public class WishEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "wish_id")
   private Long wishId;

//  주문선택 : 1(선택)
   @Column(name = "wish_order", nullable = false)
   private int wishOrder;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private MemberEntity memberEntity;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private ProductEntity productEntity;

// 장바구니 추가
   public static WishEntity toWishInsertEntity(WishDto wishDto,
                                         MemberEntity memberEntity,
                                         ProductEntity productEntity){

      WishEntity wishEntity = new WishEntity();

      wishEntity.setWishOrder(0);
      wishEntity.setMemberEntity(memberEntity);
      wishEntity.setProductEntity(productEntity);

      return wishEntity;
   }

}
