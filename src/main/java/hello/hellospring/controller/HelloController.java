package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //파라미터를 받겠다는 뜻(name이라는 파라미터)
        //@RequestParam(value = "name",required=false) 으로 바꾸는 경우 name 파라미터 꼭 안 넘겨도됨
        //default는 required=true 임
        //required
        model.addAttribute("name",name);

        return "hello-template";
    }

    //API 방식 1 - 단순 문자열 return
    //템플릿 엔진과 달리 뷰 없이 문자가 그대로 내려간다.
    @GetMapping("hello-string")
    @ResponseBody //html의 body태그가 아닌 http의 body에 이 데이터를 직접 넣어주겠다는 선언
    public String helloString(@RequestParam("name")String name){
        return "hello "+name; //ex. hello spring
    }

    /*
        localhost:8080/hello-api 호출 시
        1. 내장 톰캣 서버는 스프링에 이를 전달
        2. 스프링 컨테이너에서 hello-api를 발견했으나 @ResponseBody 어노테이션이 붙어있음
        3. 2의 어노테이션 없었으면 viewResolver를 동작시키나 @ResponseBody 어노테이션이 있는 상황
        4. @ResponseBody가 있으면 Http body에 그대로 response 데이터를 넘김
           문자열은 문자열로 반환하고, 객체는 json 방식으로 데이터 만들어서 반환(json이 디폴트)
           - @ResponseBody는 HttpMessageConverter를 동작시킴
             (HttpMessageConverter 내에 JsonConverter, StringConverter 포함됨)
     */

    //API 방식 2 - 데이터를 리턴하는 경우
    //템플릿 엔진과 달리 뷰 없이 문자가 그대로 내려간다.
    @GetMapping("hello-api")
    @ResponseBody //html의 body태그가 아닌 http의 body에 이 데이터를 직접 넣어주겠다는 선언
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 return. json 형태로 리턴 {key : value}
    }

    static class Hello{
        //A 클래스 내 static class B선언 시 A의 메소드에서 B 클래스 사용 가능
        private String name;
        //클래스 변수는 private으로 하고 getter setter 로 접근하는게 java bean 표준방식
        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    };



}
