package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링 컨테이너는 controller 어노테이션이 있으면 해당되는 객체를 생성해서 스프링에 넣어두고 관리를 한다

//@Controller, @Service, @Repository 에는 @Component 어노테이션이 들어있음
//Component Scan을 통해 스프링이 찾아와서 연결지어줌
//스프링은 스프링컨테이너에 스프링 빈 등록 시 기본으로 싱글톤으로 등록한다. (유일하게 하나만 등록해서 공유한다)
//따라서 같은 스프링 빈이면 모두 같은 인스턴스다. (설정으로 싱글톤 아니게 할 수도 있으나 특별한 경우를 제외하면 대부분 싱글톤을 쓴다.)
@Controller
public class MemberController {
//    private final MemberService memberService = new MemberService();
    //여러 서비스에서 매번 새로 생성하는 대신 하나를 생성해서 사용
    private final MemberService memberService;

    //생성자에 autowired라고 되어있다면 스프링이 스프링 컨테이너에서 MemberService를 가져와서 memberService에 연결시켜줌
    //Dependency Injection(DI)
    //MemberService 클래스에도 어노테이션 @Service를 추가해주기
    //memberService에서 사용하는 MemberRepository역시 @Repository 선언해주기
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
