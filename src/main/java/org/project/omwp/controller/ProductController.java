package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.dto.ProductDto;
import org.project.omwp.dto.ReviewDto;
import org.project.omwp.service.ProductService;
import org.project.omwp.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;


    //상품 등록시 url
    @GetMapping("/productInsert")
    public String insertGet(Model model){

        model.addAttribute("productDto", new ProductDto());
        return "product/productInsert";
    }

    @PostMapping("/productInsert")
    public String insertPost(@RequestParam("imgFile") MultipartFile files, ProductDto productDto) throws IOException {

        productService.insertProduct(productDto);
//        return "redirect:/product/productList";
        return "redirect:/";

//        System.out.println(productDto.getProductDesc()+ " <<");


    }

    // 상품 목록(가져와서 뿌리기) url
    // Grid 형식
//    @GetMapping("/productList")
//    public String productList(Model model) {
//
//        List<ProductDto> productList = productService.productListDo();
//        model.addAttribute("productList",productList);
//
//        return "product/productList";
//    }

    @GetMapping("/productList")
    public String pagingList(@RequestParam("productType") String productType,
                             @PageableDefault(page = 0,size = 36, sort = "productId",
                                     direction = Sort.Direction.ASC) Pageable pageable,
                             Model model){


        if(productType.equals("IT")||productType.equals("It")){
            List<ProductDto> productDtoList = productService.ItProductListDo(productType);
            model.addAttribute("productList",productDtoList);
//
//            Page<ProductDto> productList=productService.ITProductPagingList(pageable);
//
//            Long total=productList.getTotalElements();
//            int bockNum=4;
//            int nowPage=productList.getNumber()+1;
//            int startPage=Math.max(1,productList.getNumber()-bockNum);
//            int endPage=productList.getTotalPages();
//
//            model.addAttribute("total",total);
//            model.addAttribute("productList",productList);
//            model.addAttribute("nowPage",nowPage);
//            model.addAttribute("startPage",startPage);
//            model.addAttribute("endPage",endPage);
//
//            return "product/productList";
        }
        if(productType.equals("Design")||productType.equals("design")){
            List<ProductDto> productDtoList = productService.DesignProductListDo(productType);
            model.addAttribute("productList",productDtoList);

            return "product/productList2";
        }
        if(productType.equals("Enter")||productType.equals("enter")){
            List<ProductDto> productDtoList = productService.EnterProductListDo(productType);
            model.addAttribute("productList",productDtoList);

            return "product/productList3";
        }
        if(productType.equals("Office")||productType.equals("office")){
            List<ProductDto> productDtoList = productService.OfficeProductListDo(productType);
            model.addAttribute("productList",productDtoList);

            return "product/productList4";
        }
        if(productType.equals("Marketing")||productType.equals("marketing")){
            List<ProductDto> productDtoList = productService.MarketingProductListDo(productType);
            model.addAttribute("productList",productDtoList);

            return "product/productList5";
        }
        if(productType.equals("Invest")||productType.equals("invest")){
            List<ProductDto> productDtoList = productService.InvestProductListDo(productType);
            model.addAttribute("productList",productDtoList);

            return "product/productList6";
        }

        return null;
    }

    // 상품 목록(가져와서 뿌리기) url + paging 실패..
//    @GetMapping("/productPagingList")
//    public String pagingList(Model model, @PageableDefault(page = 0,size = 5, sort = "productId",
//            direction = Sort.Direction.DESC)Pageable pageable) {
//
//        Page<ProductDto> productList=productService.productPagingList(pageable);
//
//        Long total=productList.getTotalElements();
//        int bockNum=4;
//        int nowPage=productList.getNumber()+1;
//        int startPage=Math.max(1,productList.getNumber()-bockNum);
//        int endPage=productList.getTotalPages();
//
//        model.addAttribute("total",total);
//        model.addAttribute("productList",productList);
//        model.addAttribute("nowPage",nowPage);
//        model.addAttribute("startPage",startPage);
//        model.addAttribute("endPage",endPage);
//
//        return "/product/productPagingList";
//    }

//    // 리스트 형식
//    @GetMapping("/productList2")
//    public String productList2(Model model) {
//
//        List<ProductDto> productList = productService.productListDo();
//        model.addAttribute("productList",productList);
//
//        return "product/productList2";
//    }


    //     상품 상세페이지
    @GetMapping("/detail/{id}")
    public String search(@PathVariable("id") Long productId, Model model) {

        ProductDto product = productService.findByProduct(productId);

        if(product !=null) {
            model.addAttribute("product", product);

            List<ReviewDto> reviewDtoList = reviewService.reviewDtoListDo(productId);
            model.addAttribute("reviewDtoList", reviewDtoList);
            return "product/productDetail";
        }else{
            return "redirect:/product/productInsert";
        }

    }

    //상품수정을 하기위한 페이지
//    @GetMapping("/update/{id}")
//    public String updateView(@PathVariable("id") Long productId, Model model) {
//
//        ProductDto product = productService.searchProduct(productId);
//
//        model.addAttribute("product", product);
//
//        return "product/productUpdateView";
//    }

    //상품수정 실행
    @PostMapping("/update")
    public String updatePost(@ModelAttribute ProductDto productDto) {

        productService.productUpdate(productDto);

        return "redirect:/product/productList";
    }

    //각 카테고리 당 검색기능
    @GetMapping("/productList/search")
    public String productSearch(@RequestParam(value = "It", required = false) String productType, @RequestParam(value = "search" ,required = false) String search, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search);

        model.addAttribute("productList", productDtoList);

        return "product/productList";
    }
    @GetMapping("/productList/search2")
    public String productSearch2(@RequestParam(value = "Design", required = false) String productType, @RequestParam(value = "search2" ,required = false) String search2, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search2);

        model.addAttribute("productList", productDtoList);

        return "product/productList2";
    }
    @GetMapping("/productList/search3")
    public String productSearch3(@RequestParam(value = "Enter", required = false) String productType, @RequestParam(value = "search3" ,required = false) String search3, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search3);

        model.addAttribute("productList", productDtoList);

        return "product/productList3";
    }
    @GetMapping("/productList/search4")
    public String productSearch4(@RequestParam(value = "Office", required = false) String productType, @RequestParam(value = "search4" ,required = false) String search4, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search4);

        model.addAttribute("productList", productDtoList);

        return "product/productList4";
    }
    @GetMapping("/productList/search5")
    public String productSearch5(@RequestParam(value = "Marketing", required = false) String productType, @RequestParam(value = "search5" ,required = false) String search5, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search5);

        model.addAttribute("productList", productDtoList);

        return "product/productList5";
    }
    @GetMapping("/productList/search6")
    public String productSearch6(@RequestParam(value = "Invest", required = false) String productType, @RequestParam(value = "search6" ,required = false) String search6, Model model){

        List<ProductDto> productDtoList = productService.searchDo(productType,search6);

        model.addAttribute("productList", productDtoList);

        return "product/productList6";
    }

}
