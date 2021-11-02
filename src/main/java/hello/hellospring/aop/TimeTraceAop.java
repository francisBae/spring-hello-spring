package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP 사용 시 어디서 병목이 생기는지 찾기 쉽다(ex. repository 조회에서 오래 걸린다던지..)
//회원가입, 회원 조회 등 핵심 관심 사항과 시간 측정하는 공통 관심 사항을 분리한다
//시간을 측정하는 로직을 아래와 같이 별도 공통 로직으로 만들었기 때문에 변경 있으면 아래 로직만 변경하면 됨
//원하는 적용 대상 선택 가능(Around 사용) / ex. 서비스와 그 하위에 대해서만 측정하려면 * hello.hellospring.service..*(..))
//스프링은 AOP가 있으면 스프링 컨테이너가 스프링 빈 등록할 때 진짜 bean이 아닌 가짜 bean을 등록(ex. memberService 대신 프록시 memberService)
//가짜 spring bean 이 끝나고 joinPoint.proceed() 호출 시 실제 memberService 호출

@Aspect //AOP로 쓰려면 Aspect 추가 필요
@Component //컴포넌트 스캔 방식 사용
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //타겟팅 //실행하는 패키지명, 클래스 등 명시. 앞 문법은 패키지 하위에 다 적용한다는 뜻
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : "+joinPoint+toString());//어떤 메소드 실행하는지 정보 얻음
        try{
            return joinPoint.proceed(); //다음 메소드로 진행
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }

}
