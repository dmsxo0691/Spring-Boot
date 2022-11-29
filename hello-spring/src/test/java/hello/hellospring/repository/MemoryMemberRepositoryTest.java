package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

@AfterEach
public void afterEach(){
    repository.clearStore();
}

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //optional 에서 id를 꺼낼려면 get()을 사용한다.
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(result, member);
        //org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
    }


    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       Member result = repository.findByName("spring1").get();
       //spring1 이라는 이름으로 객체 찾기
        assertThat(result).isEqualTo(member1);  //위에서 생성한 객체와 동일한 객체인지 확인
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //전체목로 가져오기

        assertThat(result.size()).isEqualTo(2);
        //전체목록의 갯수가 총 2개인지 확인
    }
}