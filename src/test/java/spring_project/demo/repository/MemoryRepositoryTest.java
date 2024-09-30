package spring_project.demo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring_project.demo.domain.Member;
import spring_project.demo.repository.MemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 메서드 실행 후 실행되는 메서드(클리어용 콜백메서드)
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("홍길동");
        repository.save(member); // save 메서드를 이용해 멤버 저장
        // 잘 저장됐는지 결과 확인
        Member result = repository.findById(member.getId()).get();

        // 일치 여부 확인
        //System.out.println("result = " + (result == member));

        // Assertion 이용
        // Assertions.assertEquals(member, result);
        // Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("홍길동");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("임꺽정");
        repository.save(member2);

        Member result = repository.findByName("홍길동").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("홍길동");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("임꺽정");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
