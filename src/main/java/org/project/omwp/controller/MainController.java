package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.dto.ProductDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.repository.MemberRepository;
import org.project.omwp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;
    private final ProductService productService;

    @GetMapping({"","index"})
    public String index(Model model){

//  최신강의 기본 상품 표시 (it)

        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("it");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

            return "index";
        }

//   최신강의 => attachImg가 1이면 해당 썸네일 표시 (기본 : it)
    @GetMapping("newProduct/it")
    public String newProduct1(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("it");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }
    @GetMapping("newProduct/design")
    public String newProduct2(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("design");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }
    @GetMapping("newProduct/enter")
    public String newProduct3(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("enter");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }
    @GetMapping("newProduct/office")
    public String newProduct4(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("office");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }
    @GetMapping("newProduct/marketing")
    public String newProduct5(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("marketing");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }
    @GetMapping("newProduct/invest")
    public String newProduct6(Model model) {
        ProductDto productDto = new ProductDto();

        List<ProductDto> productDtoList = productService.ItProductListDo2("invest");
        if (productDtoList!=null){
            productDto = productDtoList.get(0);
        }

        model.addAttribute("productDto",productDto);

        return "index";
    }


}
