package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.dto.OrderlistDto;
import org.project.omwp.dto.WishDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.entity.WishEntity;
import org.project.omwp.service.MemberService;
import org.project.omwp.service.OrderlistService;
import org.project.omwp.service.ProductService;
import org.project.omwp.service.WishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final WishService wishService;
    private final ProductService productService;
    private final OrderlistService orderlistService;

    @GetMapping({"","/"})
    public String admin(){
        return "admin/admin";
    }
//  회원목록  
    @GetMapping("/memberList")
    public String memberList(Model model, @PageableDefault(page = 0, size = 5, sort = "userId",
                        direction = Sort.Direction.DESC)
                             Pageable pageable,
                             @RequestParam(value = "type", required = false) String type,
                             @RequestParam(value = "keyword", required = false) String keyword){

        int blockNum;
        int nowPage;
        int startPage;
        int endPage;

        Page<MemberDto> memberDtoList;

        if(type!=null && keyword!=null){
            if(type.equals("id")) {
//            회원번호(ID)로 검색할 경우
                Long userId= Long.parseLong(keyword);
//                System.out.println(userId+"<<");
                memberDtoList = memberService.searchMemberDo(userId,pageable);
            } else {
                memberDtoList = memberService.searchListDo(type,keyword,pageable);
            }

        } else {
            memberDtoList = memberService.selectMembers(pageable);
            System.out.println("memberlist null");
        }


        blockNum = 100;
        nowPage = memberDtoList.getNumber()+1;
        startPage = Math.max(1,memberDtoList.getNumber()-blockNum);   // bockNum은 총 페이지수다 큰 값
        endPage = memberDtoList.getTotalPages();

        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("memberDtoList",memberDtoList);

        return "admin/adminMemberList";
    }
//  회원검색
    @GetMapping("/search")
    public String memberSearch() {

        return "redirect:/admin/memberList";
    }

    @GetMapping("/memberDetail/{id}")
    public String memberDetail(@PathVariable(value = "id") Long id, Model model) {
        MemberDto memberDto = memberService.memberDetailDo(id);
        model.addAttribute("memberDto",memberDto);
        return "admin/adminMemberDetail";
    }
//  회원수정
    @GetMapping("/memberUpdate/{id}")
    public String memberUpdate(@PathVariable(value = "id") Long id, Model model) {
        MemberDto memberDto = memberService.memberDetailDo(id);
        model.addAttribute("memberDto",memberDto);
        return "admin/adminMemberUpdate";
    }

    @PostMapping("/memberUpdate")
    public String memberUpdateOk(@ModelAttribute MemberDto memberDto) {
        System.out.println(memberDto.getUserId()+" << userId");
        System.out.println(memberDto.getUserRole()+" << userRole");
        int rs = memberService.memberUpdateDo(memberDto);
        if (rs!=1){
            System.out.println("memberUpdate fail!");
            return null;
        } 
        return "redirect:/admin/memberDetail/"+memberDto.getUserId();
    }

//  회원삭제
    @GetMapping("/memberDelete/{id}")
    public String memberDelete(@PathVariable(value = "id") Long id, Model model) {
        MemberDto memberDto = memberService.memberDetailDo(id);
        model.addAttribute("memberDto",memberDto);
        return "admin/adminMemberDelete";
    }

    @PostMapping("/memberDelete")
    public String memberDeleteOk(@RequestParam(value = "userId") Long id,
                                 @RequestParam(value = "userPw") String pw) {

        int rs = memberService.memberDeleteDo(id,pw);
        if(rs!=1){
            System.out.println("memberDelete fail!");
            return null;
        }

        return "redirect:/admin/memberList";
    }
//  찜 목록
    @GetMapping("/wishList/{userId}")
    public String wishList(@PageableDefault(page = 0, size = 5, sort = "user_id",
            direction = Sort.Direction.DESC)
                           Pageable pageable,
                           @PathVariable(value = "userId") Long userId, Model model) {

        int blockNum;
        int nowPage;
        int startPage;
        int endPage;

        Page<WishDto> wishDtoList = wishService.selectWishes(userId,pageable);
        MemberDto memberDto = memberService.memberDetailDo(userId);

        if (wishDtoList==null){
            System.out.println("wishList null");
        }

        blockNum = 100;
        nowPage = wishDtoList.getNumber()+1;
        startPage = Math.max(1,wishDtoList.getNumber()-blockNum);   // bockNum은 총 페이지수다 큰 값
        endPage = wishDtoList.getTotalPages();

        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("wishDtoList",wishDtoList);
        model.addAttribute("memberDto",memberDto);

        return "admin/adminWishList";
    }

// 찜 삭제
    @GetMapping("/wishDelete/{userId}/{wishId}")
    public String wishDelete(@PathVariable(value = "wishId") Long wishId,
                             @PathVariable(value = "userId") Long userId,
                             Model model) {
        int rs = wishService.wishDelete(wishId);
        if(rs!=1){
            System.out.println("wishDelete fail!");
            return null;
        }
        MemberDto memberDto = memberService.memberDetailDo(userId);
        model.addAttribute("memberDto",memberDto);
        return "redirect:/admin/wishList/"+userId;
    }

//  주문처리 => Ajax
    @GetMapping("/addOrderList/{userId}/{wishId}")
    public String addOrder(@PathVariable(value = "wishId") Long wishId,
                           @PathVariable(value = "userId") Long userId,
                           Model model){

        int rs = orderlistService.addOrder(wishId);

        if (rs==1){
//            주문처리 성공시 해당 찜목록 삭제
            wishService.wishDelete(wishId);

            MemberDto memberDto = memberService.memberDetailDo(userId);
            model.addAttribute("memberDto",memberDto);
            System.out.println("addOrder success");
        } else{
            System.out.println("addOrder fail");
        }
            return "redirect:/admin/wishList/"+userId;

    }

}
