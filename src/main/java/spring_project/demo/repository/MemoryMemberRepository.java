package spring_project.demo.repository;

import spring_project.demo.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long secquence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++secquence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 들어올 수도 있는 경우 Optional을 이용해서 감싸줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 조건에 일치하는 요소 1개 리턴(순서x)
    }

    @Override
    public List<Member> findAll() {
        // map.values() : collection 형태로 전체 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // map.clear(): 전체 삭제
        store.clear();
    }
}

