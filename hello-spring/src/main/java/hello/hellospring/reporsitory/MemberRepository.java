package hello.hellospring.reporsitory;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional 로 감싸서 반환 -> null 값 자동 처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
