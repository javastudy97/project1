package org.project.omwp.controller;

import org.project.omwp.dto.MemberDto;
import org.project.omwp.entity.MemberEntity;
import org.project.omwp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping({"","index"})
    public String index(){
        return "index";
    }


}
