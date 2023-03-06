package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.ProductDto;
import org.project.omwp.entity.ImgEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.repository.ImgRepository;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;
    private final MemberRepository memberRepository;

    // 상품등록 추가시
    @Transactional //추가, 삭제, 수정시 적용
    public void insertProduct(ProductDto productDto) throws IOException {

//        System.out.println(productDto.getProductDesc()+ " <<");
        //이미지가 없을 때
        if (productDto.getImgFile().isEmpty()){

            ProductEntity productEntity = ProductEntity.toProductEntity(productDto);
            productRepository.save(productEntity);
        }
        //이미지가 있을 때
        else {

            //1. 이미지 받아오기

            MultipartFile multipartFile = productDto.getImgFile();
            String originalImgName = multipartFile.getOriginalFilename(); //원본파일이름
            UUID uuid = UUID.randomUUID(); // 랜덤 이미지 이름

            String newImgName = uuid + "_" +originalImgName; //새로운 이미지 이름
            String filePath = "C:/saveFiles/" + newImgName; //이미지 경로

            multipartFile.transferTo(new File(filePath)); // 이미지 경로 탐색

            ProductEntity productEntity = ProductEntity.toProductEntityGoImgInclude(productDto);
            Long productId = productRepository.save(productEntity).getProductId();

            Optional<ProductEntity> productEntity1 = productRepository.findById(productId);
            ProductEntity productEntity2 = productEntity1.get(); //id에 해당하는 상품

            ImgEntity imgEntity = ImgEntity.toImgEntity(productEntity2, originalImgName, newImgName);
            imgRepository.save(imgEntity); // 파일저장
        }



    }

//    // 상품 목록 페이지 가져오기
//    public List<ProductDto> productListDo() {
//
//        List<ProductEntity> productEntityList = productRepository.findAll();
//        List<ProductDto> productDtoList = new ArrayList<>();
//
//        for (ProductEntity productEntity : productEntityList) {
//            productDtoList.add(ProductDto.toProductDto(productEntity));
//        }
//        return productDtoList;
//    }

    // 상품 목록 페이지 페이징(It)
    public Page<ProductDto> ProductPagingList(String productType,Pageable pageable) {

        //Page<ProductEntity> ItProductEntityList = productRepository.findAll(pageable);
        Page<ProductEntity> ProductEntityList = productRepository.findAllByProductType(productType,pageable);
        Page<ProductDto> ProductDtoList = ProductEntityList.map(ProductDto::toProductDto);

        return ProductDtoList;
    }

//    public List<ProductDto> ItProductListDo(String productType) {
//
//        List<ProductEntity> ItProductEntityList = productRepository.findByProductType(productType);
//        List<ProductDto> ItProductDtoList = new ArrayList<>();
//
//        for(ProductEntity productEntity : ItProductEntityList){
//            ItProductDtoList.add(ProductDto.toProductDto(productEntity));
//        }
//
//
//        return ItProductDtoList;
//
//    }




    // 상품 목록 상세 페이지 가져오기
    public ProductDto findByProduct(Long id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);


        if (optionalProductEntity.isPresent()) {
            return ProductDto.toProductDto(optionalProductEntity.get());
        } else {
            return null;
        }
    }

    // 상품 수정 시
    @Transactional
    public void productUpdate(ProductDto productDto) {

        ProductEntity productEntity = ProductEntity.toProductUpdateEntity(productDto);

        productRepository.save(productEntity);
    }

    // 상품 상세페이지
    // 상품 목록 상세 페이지 가져오기(It)
    public List<ProductDto> ItProductListDo(String productType) {

        List<ProductEntity> ItProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> ItProductDtoList = new ArrayList<>();

        for(ProductEntity productEntity : ItProductEntityList){
            ItProductDtoList.add(ProductDto.toProductDto(productEntity));
        }


        return ItProductDtoList;

    }

    // 상품 목록 상세 페이지 가져오기(Design)
    public List<ProductDto> DesignProductListDo(String productType) {
        List<ProductEntity> DesignProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> DesignProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : DesignProductEntityList){
            DesignProductDtoList.add(ProductDto.toProductDto(productEntity));
        }

        return  DesignProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기(Enter)
    public List<ProductDto> EnterProductListDo(String productType) {
        List<ProductEntity> EnterProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> EnterProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : EnterProductEntityList){
            EnterProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return EnterProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기(Office)
    public List<ProductDto> OfficeProductListDo(String productType) {
        List<ProductEntity> OfficeProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> OfficeProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : OfficeProductEntityList){
            OfficeProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return OfficeProductDtoList;

    }

    // 상품 목록 상세 페이지 가져오기(Marketing)
    public List<ProductDto> MarketingProductListDo(String productType) {
        List<ProductEntity> MarketingProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> MarketingProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : MarketingProductEntityList){
            MarketingProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return MarketingProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기(Invest)
    public List<ProductDto> InvestProductListDo(String productType) {
        List<ProductEntity> InvestProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> InvestProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : InvestProductEntityList){
            InvestProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return InvestProductDtoList;

    }

    public ProductDto orderProduct(Long id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if(!optionalProductEntity.isPresent()){
            return null;
        }

        ProductDto productDto = ProductDto.toProductDto2(optionalProductEntity.get());

        return productDto;

    }

    public List<ProductDto> findAllProduct() {

        List<ProductDto> productDtoList = new ArrayList<>();

        List<ProductEntity> productEntityList = productRepository.findAll();

        for(ProductEntity productEntity : productEntityList){

            productDtoList.add(ProductDto.toProductDto2(productEntity));

        }

        return productDtoList;

    }

    public List<ProductDto>  searchDo(String productType,String search) {
        List<ProductDto> productDtoList = new ArrayList<>();

        List<ProductEntity> productEntityList = productRepository.findByProductNameContainingAndProductTypeContaining(search,productType);

        for (ProductEntity productEntity :  productEntityList){
            productDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return productDtoList;
    }


    public Page<ProductDto> PagingsearchDo(String productType, String search, Pageable pageable) {
        //Page<ProductEntity> ItProductEntityList = productRepository.findAll(pageable);
        Page<ProductEntity> ProductEntityList = productRepository.findByProductNameContainingAndProductTypeContaining(search,productType,pageable);
        Page<ProductDto> ProductDtoList = ProductEntityList.map(ProductDto::toProductDto);

        return ProductDtoList;
    }

    //관리자 페이지용 모든 상품 리스트

    public List<ProductDto> productListDo() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductEntity productEntity : productEntityList) {
            productDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return productDtoList;
    }
    
    //페이징
    public Page<ProductDto> ProductAllPagingList(Pageable pageable) {
        Page<ProductEntity> ProductEntityList = productRepository.findAll(pageable);
        Page<ProductDto> ProductDtoList = ProductEntityList.map(ProductDto::toProductDto);

        return ProductDtoList;
    }


    //상품 삭제 시
    @Transactional
    public void productDeleteDo(Long productId) {
        productRepository.deleteById(productId);
    }

    // 상품 수정 시
    @Transactional
    public void productUpdateDo(ProductDto productDto) {
        if (productDto.getImgNewName().isEmpty()) {
            ProductEntity productEntity = ProductEntity.toProductUpdateEntity(productDto);
            productRepository.save(productEntity);
        }else {
            ProductEntity productEntity = ProductEntity.toProductUpdateEntity2(productDto);
            productRepository.save(productEntity);
        }

    }

    @Transactional
    public void reviewCountUp(Long productId) {
        productRepository.upReviewCount(productId);
    }


}
