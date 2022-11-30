package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long >, MemberRepository {

    //'select m from Member m where m.name =?'과 같은 JPQL 쿼리를 자동 생성
    @Override
    Optional<Member> findByName(String name);
}
