package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false)
	void save() {
		Member member = new Member();
		member.setUsername("memberA");
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장

	}

	@Test
	void find() {
	}
}