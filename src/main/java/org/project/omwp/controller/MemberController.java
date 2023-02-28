package org.project.omwp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.omwp.checked.CheckUserEmail;
import org.project.omwp.checked.CheckUserName;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.service.MemberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;
    private final CheckUserEmail checkUserEmail;
    private final CheckUserName checkUserName;


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

    @GetMapping("/detail")
    public String detail(Principal principal,Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        model.addAttribute("memberDto",memberDto);

        return "member/detail";
    }

    @GetMapping("/update")
    public String update(Principal principal,Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        model.addAttribute("memberDto",memberDto);

        return "member/memberUpdateView";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDto memberDto){

        int rs =  memberService.memberUpdateDo2(memberDto);
        if (rs!=1){
            return null;
        }
        return "member/detail";
    }


    @GetMapping("/delete")
    public String delete(Principal principal,Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        model.addAttribute("memberDto",memberDto);

        return "member/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute MemberDto memberDto){

        Long id = memberDto.getUserId();
        String pw = memberDto.getUserPw();

        String userEmail = memberDto.getUserEmail();

        MemberDto memberDto2 = memberService.findById(userEmail);


        boolean bl = passwordEncoder.matches(pw, memberDto2.getUserPw());

        if(bl== false){

            return "/member/detail";
        }

        memberService.memberDeleteDo2(id);

        return "redirect:/logout";
    }

    // 커스텀 유효성 검사
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators((Validator) checkUserEmail);
        binder.addValidators((Validator) checkUserName);
    }

    // 이메일 찾기(페이지 아동)
    @GetMapping("/findEmail")
    public String findEmail(){

        return "member/findEmail";
    }

    // 이메일 찾기 실행
    @GetMapping("/findEmailOk")
    public String findEmailOk(){

        return "member/findEmailOk";

    }



}
