package org.project.omwp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "review_count", columnDefinition = "integer default 0")
    private int reviewCount;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WishEntity> wishEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderlistEntity> orderlistEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ImgEntity> imgEntities = new ArrayList<>();




}
