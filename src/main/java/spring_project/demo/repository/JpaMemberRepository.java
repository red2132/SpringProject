package spring_project.demo.repository;

import jakarta.persistence.EntityManager;
import spring_project.demo.domain.Member;

import java.util.List;
import java.util.Optional;
public class JpaMemberRepository implements MemberRepository {
    // 자동으로 만들어진 EntityManager를 이용
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        // EntityManager 주입
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member); // 영구 저장
        return member;
    }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // find로 찾기
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        // m이라는 객체에 데이터 담음(알아서 짝지어짐)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
