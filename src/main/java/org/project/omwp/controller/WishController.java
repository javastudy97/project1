package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.omwp.dto.*;
import org.project.omwp.entity.ImgEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.repository.ImgRepository;
import org.project.omwp.service.MemberService;
import org.project.omwp.service.OrderlistService;
import org.project.omwp.service.ProductService;
import org.project.omwp.service.WishService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    private final ProductService productService;

    private final OrderlistService orderlistService;

    private final ImgRepository imgRepository;


    @GetMapping("/cart")
    public String cart(Principal principal, Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();



        List<WishDto> wishDtoList = wishService.wishList(userId);

        model.addAttribute("wishDtoList",wishDtoList);

        return "wish/wishList";
    }



    @PostMapping("/order")
    public String order(@ModelAttribute("productDto") WishDto wishDto, Model model){

        Long id = wishDto.getProductId();

        ProductDto productDto = productService.orderProduct(id);


        model.addAttribute("productDto",productDto);

        Long wishId = wishDto.getWishId();

        wishService.deleteWish(wishId);

        return "wish/order";
    }

    @PostMapping("/orderList")
    public String orderList(@ModelAttribute("productDto") ProductDto productDto, Principal principal, Model model) throws IOException {

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();

        Long productId = productDto.getProductId();

        orderlistService.insertOrder(userId,productId);


        return "redirect:/wish/purchased";
    }



    @GetMapping("/purchased")
    public String purchased(Principal principal, Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();

        List<OrderlistDto> orderlistDtoList = orderlistService.selectAllOrder(userId);

        model.addAttribute("orderlistDtoList",orderlistDtoList);

        return "wish/purchased";

    }

    @PostMapping("/cancel")
    public String cancel(@ModelAttribute("orderlistDto") OrderlistDto orderlistDto){

        Long id = orderlistDto.getOrderlistId();

        orderlistService.cancelOrder(id);


        return "redirect:/wish/cancelList";
    }

    @GetMapping("/cancelList")
    public String cancel(Principal principal, Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();

        List<OrderlistDto> orderlistDtoList = orderlistService.selectAllCancel(userId);

        model.addAttribute("orderlistDtoList",orderlistDtoList);


        return "wish/cancelList";
    }

    @GetMapping("/deleteWish/{id}")
    public String deleteWish(@PathVariable("id")Long id){

        wishService.deleteWish(id);

        return "redirect:/wish/cart";
    }

    @PostMapping("/deleteCancel")
    public String deleteCancel(@ModelAttribute("orderlistDto")OrderlistDto orderlistDto, Model model){

        orderlistService.deleteCancelList(orderlistDto);

        return "redirect:/wish/cancelList";

    }

    @GetMapping("/putCart/{productId}")
    public String putCart(@PathVariable("productId") Long productId, Principal principal, Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();

        wishService.insertWish(userId, productId);

        return "redirect:/wish/cart";
    }

    //상품상세 구매하기 눌렀을때
    @GetMapping("/orderList/{productId}")
    public String orderListInsert(@PathVariable("productId") Long productId, Principal principal, Model model, RedirectAttributes redirect) throws IOException {

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();



        orderlistService.insertOrder(userId, productId);



        return "redirect:/wish/purchased";
    }


}
