package spring_project.demo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_project.demo.domain.Member;
import spring_project.demo.repository.MemberRepository;
import spring_project.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 네이밍 롤, repository는 단순 작업(회원정보 저장, 삭제...), service는 전체적인 작업 느낌(ex. 회원가입)으로 짓는게 좋음

@Transactional // jpa는 데이터가 변경될 때 트랜잭션 안에서 실행돼야 함
public class MemberService {
    private final MemberRepository memberRepository;

    // 생성자를 통해 repository 주입
    // @Autowired: spring bean이 등록되지 않으면 사용할 수 없음
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateName(member); // "Ctrl + Alt + M" 단축키로 메서드 추출

        // 동명이인 아닐 경우 데이터 저장
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 동명이인 체크
     */
    private void validateName(Member member) {
        // 동명이인 가입 불가(Ctrl + Alt + V로 표현식을 변수로 추출)
        // memberRepository.findByName(member.getName())의 반환값은 Optional<Member>
        // Optional의 ifPresent로 해당 이름이 존재하는지 확인 후 예외 발생시킴
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
