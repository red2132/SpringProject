package spring_project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring_project.demo.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // service를 자동 주입(의존성 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
