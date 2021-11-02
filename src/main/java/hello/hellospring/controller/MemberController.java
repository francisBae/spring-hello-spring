package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        System.out.println("memberService = "+memberService.getClass());
        //memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$9cbbca40
        //=>memberService를 복제해서 코드 조작하는 기술을 쓴 것
    }

    //url에 /members/new 로 들어가면 get방식 수행
    //members/createMemberForm.html 로 이동
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //url에 /members/new 로 들어가면 get방식 수행
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈 화면으로 다시
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members); //model에 추가하면 아래 return으로 같이 넘어감
        return "members/memberList";
    }

}

//DI 에는 생성자 주입방식, 셋터주입방식, 필드주입방식이 있다.
//생성자 주입이 가장 추천하는 방식
//셋터주입 방식은 public으로 셋터 메소드가 선언되어있어야함. 그러면 누구나 호출 가능해서 권장X

