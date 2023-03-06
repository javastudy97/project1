package org.project.omwp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project.omwp.dto.ProductDto;
import org.project.omwp.repository.ImgRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 1씩 증가
    @Column(name = "product_id")
    private Long productId;

    //  카테고리 : it / enter(예체능) / design(디자인) / office(사무) / marketing(마케팅) / invest(재테크)
    @Column(name = "product_type", nullable = false)
    private String productType;

    //  상품명 => not null, unique
    @Column(name = "product_name" ,nullable = false, unique = true)
    private String productName;

    //  상품설명
    @Column(name = "product_desc" ,nullable = false)
    private String productDesc;

    //  상품가격
    @Column(name = "product_price", nullable = false)
    private int productPrice;

    //  상품등록일
    @CreationTimestamp
    @Column(name = "product_create", updatable = false)
    private LocalDateTime productCreate;

    //  리뷰 수
    @Column(name = "review_count")
    private int reviewCount;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WishEntity> wishEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderlistEntity> orderlistEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ImgEntity> imgEntities = new ArrayList<>();

    @Column(nullable = false)
    private int attachImg; //이미지 유무(1,0)

    // 상품등록
    public static ProductEntity toProductEntity(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity();
        System.out.println(productDto.getProductDesc()+ " <<");
        // id 정보 필요 X
        productEntity.setProductType(productDto.getProductType());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductDesc(productDto.getProductDesc());
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setAttachImg(0);
        productEntity.setReviewCount(0);  // 기본 리뷰수 0으로 설정 (int 타입은 null 허용시 에러)
        System.out.println("ddddd1");
        return productEntity;
    }
    public static ProductEntity toProductEntityGoImgInclude(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        System.out.println(productDto.getProductType()+ " <<");
        // id 정보 필요 X
        productEntity.setProductType(productDto.getProductType());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductDesc(productDto.getProductDesc());
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setAttachImg(1);
        System.out.println("ddddd1");

        return productEntity;
    }

    // 상품수정
    public static ProductEntity toProductUpdateEntity(ProductDto productDto){
        ProductEntity productEntity=new ProductEntity();

        // id 정보 필요 O
        productEntity.setProductId(productDto.getProductId());
        productEntity.setProductType(productDto.getProductType());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductDesc(productDto.getProductDesc());
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setReviewCount(productDto.getReviewCount());
        System.out.println(productDto.getAttachImg()+"<<<<<<");
        System.out.println(productDto.getAttachImg()+"<<<<<<");
        productEntity.setAttachImg(0);

        return productEntity;
    }

    public static ProductEntity toProductUpdateEntity2(ProductDto productDto){
        ProductEntity productEntity=new ProductEntity();

        // id 정보 필요 O
        productEntity.setProductId(productDto.getProductId());
        productEntity.setProductType(productDto.getProductType());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductDesc(productDto.getProductDesc());
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setReviewCount(productDto.getReviewCount());
        System.out.println(productDto.getAttachImg()+"<<<<<<");
        System.out.println(productDto.getAttachImg()+"<<<<<<");
        productEntity.setAttachImg(1);

        return productEntity;
    }


}
