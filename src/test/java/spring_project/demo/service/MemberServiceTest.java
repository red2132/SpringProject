package spring_project.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring_project.demo.domain.Member;
import spring_project.demo.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // 테스트는 그냥 한글로 해도 상관 없음
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 각 메서드 실행 전, repository를 선언 후, service에서 해당 리포지토리 사용
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 각 메서드 실행 후, 실행되는 메서드(클리어용 콜백메서드)
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("홍길동");

        // when
        Long saveId = memberService.join(member);
        
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("홍길동");

        Member member2 = new Member();
        member2.setName("홍길동");

        //when(예외 발생이 일치하는지 체크)
        memberService.join(member1);

        // assertThrows(IllegalStateException.class,() -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class,
                                () -> memberService.join(member2));
        
        // 메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}