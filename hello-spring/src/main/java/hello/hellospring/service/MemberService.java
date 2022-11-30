package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;
    //service를 만들기 위해서는  Repository 있어야함


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {

        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    //같은 이름이 있는 중복회원 X
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    //혹시나 null 값이 있으며 Optional 로 감싸서 반환
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        return memberRepository.findAll();

    }

    //회원조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}

