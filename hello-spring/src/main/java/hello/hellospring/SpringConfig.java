//package hello.hellospring;
//
//import hello.hellospring.reporsitory.MemberRepository;
//import hello.hellospring.reporsitory.MemoryMemberRepository;
//import hello.hellospring.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringConfig {
//    // 직접 설정 파일을 등록하여 스프링 컨테이너에 스프링 빈을 등록하는 방법
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
//}
