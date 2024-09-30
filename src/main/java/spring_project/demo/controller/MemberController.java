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
/*
* 의존성 주입 방법
* 1. 필드 주입 -> 수정이 어려움
* 2. setter 주입 -> setter가 열려있기 때문에 초기 세팅 이후에도 계속 누군가가 호출할 수 있음
* 3. 생성자 주입 -> 수정이 쉽고 초기 세팅 이후로 변경되지 않음 -> 권장!!
*/
