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
    public Page<ProductDto> ITProductPagingList(Pageable pageable) {



        //Page<ProductEntity> ItProductEntityList = productRepository.findAll(pageable);
        Page<ProductEntity> ItProductEntityList = productRepository.findAll(pageable);

        Page<ProductDto> ItProductDtoList = ItProductEntityList.map(ProductDto::toProductDto);

        return ItProductDtoList;
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
    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> ItProductListDo(String productType) {

        List<ProductEntity> ItProductEntityList = productRepository.findByProductType(productType);
        List<ProductDto> ItProductDtoList = new ArrayList<>();

        for(ProductEntity productEntity : ItProductEntityList){
            ItProductDtoList.add(ProductDto.toProductDto(productEntity));
        }


        return ItProductDtoList;

    }

    public List<ProductDto> ItProductListDo2(String productType) {
        System.out.println(productType+"<<<<<<<< type");

        List<ProductEntity> ItProductEntityList = productRepository.findByProductTypeDesc(productType);
        List<ProductDto> ItProductDtoList = new ArrayList<>();

        for(ProductEntity productEntity : ItProductEntityList){
            ItProductDtoList.add(ProductDto.toProductDto3(productEntity));
        }


        return ItProductDtoList;

    }

    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> DesignProductListDo(String productType) {
        List<ProductEntity> DesignProductEntityList = productRepository.findByProductType(productType);
        List<ProductDto> DesignProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : DesignProductEntityList){
            DesignProductDtoList.add(ProductDto.toProductDto(productEntity));
        }

        return  DesignProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> EnterProductListDo(String productType) {
        List<ProductEntity> EnterProductEntityList = productRepository.findByProductType(productType);
        List<ProductDto> EnterProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : EnterProductEntityList){
            EnterProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return EnterProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> OfficeProductListDo(String productType) {
        List<ProductEntity> OfficeProductEntityList = productRepository.findByProductType(productType);
        List<ProductDto> OfficeProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : OfficeProductEntityList){
            OfficeProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return OfficeProductDtoList;

    }

    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> MarketingProductListDo(String productType) {
        List<ProductEntity> MarketingProductEntityList = productRepository.findByProductType(productType);
        List<ProductDto> MarketingProductDtoList = new ArrayList<>();

        for (ProductEntity productEntity : MarketingProductEntityList){
            MarketingProductDtoList.add(ProductDto.toProductDto(productEntity));
        }
        return MarketingProductDtoList;
    }

    // 상품 목록 상세 페이지 가져오기
    public List<ProductDto> InvestProductListDo(String productType) {
        List<ProductEntity> InvestProductEntityList = productRepository.findByProductType(productType);
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


    public void ItProductListDo2() {
    }
}
