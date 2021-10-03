package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //기본 경로(/)에 아무 것도 없는 경우는 index.html을 웰컴페이지로 최초 호출하나
    //스프링 컨테이너를 우선적으로 탐색하고 나서 정적컨텐츠를 확인하기 때문에 아래 home을 수행
    @GetMapping("/")
    public String home(){
        return "home";
    }


}
