package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    //생성자 연결
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //회원등록 버튼 누를시 가입 form으로 이동
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //회원가입
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    //회원 조회
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.fineMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
