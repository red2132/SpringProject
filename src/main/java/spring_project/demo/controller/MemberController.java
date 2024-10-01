package spring_project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring_project.demo.domain.Member;
import spring_project.demo.domain.MemberForm;
import spring_project.demo.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // service를 자동 주입(의존성 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public  String createForm() {
        // 해당 화면으로 이동
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // 멤버 객체에 이름 세팅
        
        memberService.join(member); // 멤버 정보 등록
        return "redirect:/"; // 홈 화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
/*
* 의존성 주입 방법
* 1. 필드 주입 -> 수정이 어려움
* 2. setter 주입 -> setter가 열려있기 때문에 초기 세팅 이후에도 계속 누군가가 호출할 수 있음
* 3. 생성자 주입 -> 수정이 쉽고 초기 세팅 이후로 변경되지 않음 -> 권장!!
*/
