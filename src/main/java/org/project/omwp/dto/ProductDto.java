package org.project.omwp.dto;

import lombok.*;
import org.project.omwp.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long productId;

    private String productType;

    private String productName;

    private String productDesc;

    private int productPrice;

    private LocalDateTime productCreate;

    private int reviewCount;

    private MultipartFile imgFile;

    private String imgOldName;
    private String imgNewName;
    private int attachImg; //이미지 유무(1,0)


//    public ProductDto(Long id, String type,
//                      String name, String desc,
//                      int price, int reviewCount){
//
//        this.productId =id;
//        this.productType=type;
//        this.productName=name;
//        this.productDesc=desc;
//        this.productPrice=price;
//        this.reviewCount=reviewCount;
//
//        //나중에 Role 추가
//    }

    // Entity -> Dto
    public static ProductDto toProductDto(ProductEntity productEntity){

        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getProductId());
        productDto.setProductType(productEntity.getProductType());
        productDto.setProductName(productEntity.getProductName());
        productDto.setProductDesc(productEntity.getProductDesc());
        productDto.setReviewCount(productEntity.getReviewCount());
        productDto.setProductPrice(productEntity.getProductPrice());
        productDto.setProductCreate(productEntity.getProductCreate());

        //이미지가 없을때
        if (productEntity.getAttachImg() == 0){
            productDto.setAttachImg(productEntity.getAttachImg());
        }else{ //이미지가 있을때
            productDto.setAttachImg(productEntity.getAttachImg());

            //이미지 불러오기
            productDto.setImgOldName(productEntity.getImgEntities().get(0).getImgOldName());
            productDto.setImgNewName(productEntity.getImgEntities().get(0).getImgNewName());

        }

        // 나중에 Role 추가

        return  productDto;
    }

    public static ProductDto toProductDto2(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getProductId());
        productDto.setProductType(productEntity.getProductType());
        productDto.setProductName(productEntity.getProductName());
        productDto.setProductDesc(productEntity.getProductDesc());
        productDto.setReviewCount(productEntity.getReviewCount());
        productDto.setProductPrice(productEntity.getProductPrice());

        productDto.setProductCreate(productEntity.getProductCreate());
        // 나중에 Role 추가

        return  productDto;
    }

    public static ProductDto toProductDto3(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getProductId());
        productDto.setProductType(productEntity.getProductType());
        productDto.setProductName(productEntity.getProductName());
        productDto.setProductDesc(productEntity.getProductDesc());
        productDto.setReviewCount(productEntity.getReviewCount());
        productDto.setProductPrice(productEntity.getProductPrice());
        productDto.setProductCreate(productEntity.getProductCreate());
        productDto.setProductCreate(productEntity.getProductCreate());

        //이미지가 없을때
        if (productEntity.getAttachImg() == 0){
            productDto.setAttachImg(productEntity.getAttachImg());
        }else{ //이미지가 있을때
            productDto.setAttachImg(productEntity.getAttachImg());

            //이미지 불러오기
            productDto.setImgOldName(productEntity.getImgEntities().get(0).getImgOldName());
            productDto.setImgNewName(productEntity.getImgEntities().get(0).getImgNewName());

        }

        // 나중에 Role 추가

        return  productDto;
    }
}
