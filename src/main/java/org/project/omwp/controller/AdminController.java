package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.dto.OrderlistDto;
import org.project.omwp.dto.WishDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.service.MemberService;
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

        if (wishDtoList==null){
            System.out.println("wishList null");
        }
//
        blockNum = 100;
        nowPage = wishDtoList.getNumber()+1;
        startPage = Math.max(1,wishDtoList.getNumber()-blockNum);   // bockNum은 총 페이지수다 큰 값
        endPage = wishDtoList.getTotalPages();

        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("wishDtoList",wishDtoList);

        return "admin/adminWishList";
    }

//    @GetMapping("/wishList/{userId}")
//    public String wishList(@PathVariable(value = "userId") Long userId, Model model) {
//
//        List<WishDto> wishDtoList = wishService.selectWishes(userId);
//
//        if (wishDtoList==null){
//            System.out.println("wishList null");
//        }
//
//        model.addAttribute("wishDtoList",wishDtoList);
//
//        return "admin/adminWishList";
//    }
//  주문처리
//    @PostMapping("/orderDetail")
//    public String orderInsert(@RequestParam()) {
//
//        return "redirect:admin/orderDetail"+ OrderlistDto;
//
//    }

}
