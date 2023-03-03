package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.ReviewDto;
import org.project.omwp.service.ProductService;
import org.project.omwp.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductService productService;

    @PostMapping("/reviewWrite")
    public String reviewWrite(@ModelAttribute ReviewDto reviewDto, Model model){

        Long result = reviewService.insertReviewDo(reviewDto);
        System.out.println("userId"+reviewDto.getUserId());
        System.out.println("productId"+reviewDto.getProductId());

        if (result !=0) {
            productService.reviewCountUp(reviewDto.getProductId());
        }


        List<ReviewDto> reviewDtoList =
                reviewService.reviewDtoListDo(reviewDto.getProductId());

        model.addAttribute("reviewDtoList", reviewDtoList);

        return "redirect:/product/detail/"+reviewDto.getProductId();
    }
}
