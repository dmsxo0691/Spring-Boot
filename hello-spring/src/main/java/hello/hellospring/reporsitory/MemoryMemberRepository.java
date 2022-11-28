package hello.hellospring.reporsitory;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 회원을 저장할 하나의 데이터베이스
    private static Long sequence = 0L;
    // 일련 번호 증가

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 멤버를 저장할 때 일련번호 1씩 증가
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 같은 name 을 가지고 있는 객체를 찾으면 반환, 없으면 null 반환
        // 람다식으로 사용되었으며 필터기능을 줘서 member.getName 값이 파라미터로 넘어온 name값이랑 같은 지 확인
// name이 같은 경우에는 필터링이 된다.
// findAny()는 결과가 Optional로 반환된다. Map에서 루프를 다 돌면서 하나 찾으면 찾은 값을 반환
// 끝까지 돌렸는데 없으면 Optional에 null이 포함되서 반영된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // Optional 이란? 자바 8에 들어간 기능 : 반환값이 null인 경우
    // Optional 로 null을 감싸서 반환하는 게 주류

    public void clearStore() {
        store.clear(); // 비워주는 메서드
    }
}
