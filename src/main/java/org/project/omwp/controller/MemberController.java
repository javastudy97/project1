package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    // 회원가입 페이지 이동
    @GetMapping("/signUp")
    public String signUp(Model model){

        model.addAttribute("memberDto", new MemberDto());
        return "member/signUp";
    }


    // 회원가입 실행
    @PostMapping("/signUp")
    public String signUp2(@Valid MemberDto memberDto, BindingResult result){
        if(result.hasErrors()){
            return "member/signUp";
        }
        memberService.memberInsert(memberDto);
        return "redirect:/";

    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "member/login";
    }

}
