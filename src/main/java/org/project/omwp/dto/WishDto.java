package org.project.omwp.dto;

import lombok.*;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.entity.WishEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishDto {

    private Long wishId;
    private int wishOrder;
    private Long userId;
    private Long productId;
    private String productType;
    private String productName;
    private String productDesc;
    private int productPrice;

    public static WishDto toWishDto(WishEntity wishEntity) {
        WishDto wishDto = new WishDto();

        wishDto.setWishId(wishEntity.getWishId());
        wishDto.setWishOrder(wishEntity.getWishOrder());
        wishDto.setUserId(wishEntity.getMemberEntity().getUserId());
        wishDto.setProductId(wishEntity.getProductEntity().getProductId());
        wishDto.setProductType(wishEntity.getProductEntity().getProductType());
        wishDto.setProductName(wishEntity.getProductEntity().getProductName());
        wishDto.setProductDesc(wishEntity.getProductEntity().getProductDesc());
        wishDto.setProductPrice(wishEntity.getProductEntity().getProductPrice());

        return wishDto;
    }
}
