package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    //Controller은 웹 어플리케이션의 첫 진입점
    //어노테이션으로 Controller를 명시해줘야 Controller 기능을 함

    @GetMapping("hello")
    public String hello(Model model){
        //웹 어플리케이션에서 '/hello' 가 들어오면 이 메소드를 호출한다.

        /*
        1. 웹브라우저에서 localhost:8080/hello 호출 시
        2. 내장 톰캣서버에서 스프링에 이 요청을 던진다. (GetMapping에 hello로 된 부분)
           - get방식에 대한 mapping임 (postmapping은 다른듯)
        3. HelloController에 있는 hello 메소드가 실행됨
        4. Spring에서 만들어서 넘겨준 model에 data라는 이름으로 hello라는 값을 넣어준다
        5. resources 내에 hello (여기서는 templates/hello.html)를 찾아가서 해당 화면에 모델 넘긴다
           - Controller에서 리턴 값으로 문자열을 반환하면 뷰 리졸버(view resolver)가 화면을 찾아서 처리한다.
           - 스프링부트 템플릿 엔진의 기본 viewName 매핑 => 'resources:templates/'+{ViewName}+'.html'
        6. 타임리프 템플릿 엔진처리(렌더링시킴)
         */


        model.addAttribute("data","hello!!");
        return "hello";
    }


}
