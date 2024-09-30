package spring_project.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_project.demo.repository.MemberRepository;
import spring_project.demo.repository.MemoryMemberRepository;
import spring_project.demo.service.MemberService;

// 자바코드로 직접 빈 등록함
// 리포지토리 변경(db변경할 때) 손쉽게 갈아끼울 수 있음
@Configuration
public class SpringConfig {
   //  memberService와 memberRepository를 Bean에 등록
    
    @Bean
    public MemberService memberService() {
        // 등록되어 있는 repository를 service에 주입
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
