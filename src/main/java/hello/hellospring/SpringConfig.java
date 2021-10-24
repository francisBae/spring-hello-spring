package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration, Bean 사용하면 컴포넌트 스캔, 자동의존관계 설정이 아닌
//자바코드로 직접 스프린 빈 등록해서 사용 가능
@Configuration
public class SpringConfig {

    //스프링빈에 등록하라는 걸로 스프링이 인식
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}

//configuration을 읽고 Bean 선언된 MemberService와 MemberRepository를 스프링에 등록
//스프링빈에 등록되어있는 MemberRepository를 MemberService 클래스의 memberRepository에 넣어줌

//실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리와 같은 코드는 컴포넌트 스캔을 사용
//정형화되지 않거나, 상황에 따라 구현 클래서 변경 필요한 경우 설정 통해 스프링빈으로 등록

//스프링 빈으로 등록하면 위 public MemberRepository에서 MemoryMemberRepository를 다른 리포지토리로 쉽게 교체 가능
//(컴포넌트 스캔 방식이면 코드 수정이 조금 필요함)