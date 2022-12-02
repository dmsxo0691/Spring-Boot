package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext //스프링이 EntityManager 를 만들어서 em에 주입
    private final EntityManager em;

    //public MemberRepository(EntityManager em){
    //   this.em = em;
    //}

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){ //아이디 단 건 조회
        return em.find(Member.class, id);
    }

    //리스트 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();

    }
    //이름 검색
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name).getResultList();
    }

}
