package spring_project.demo.repository;

import org.springframework.stereotype.Repository;
import spring_project.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional : null도 들어올 수 있다는 뜻
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
