package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.*; //static import

//테스트 클래스는 굳이 public으로 할 필요는 없다
//테스트 클래스의 패키지는 테스트할 클래스와 같이 맞춰준다(대신 경로는 test 아래)
//명명규칙은 주로 테스트할 클래스이름 +Test (아래 명칭 참고)
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    //각 테스트 끝나면 수행될 메소드
    @AfterEach
    public void afterEach(){
        repository.clearStore(); //테스트 수행 끝날때마다 비워주기
    }


    //save 기능 테스트해보기
    //Test 어노테이션 붙이기
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //optional 타입에서 값을 꺼낼 때는 get 사용
        //(findById의 리턴이 optional형)

//        System.out.println("result = "+(result==member));

        //1. org.junit.jupiter.api.Assertions 사용 시
//        Assertions.assertEquals(result, member); //(expected, actual) ==> 동일한지 확인
//        Assertions.assertEquals(result, null); //다른 경우 테스트
        //동일하면 실행 시 Test Results가 녹색 표기가 되고, 다르면 빨간 표시

        //2. org.assertj.core.api.Assertions 사용 시
//        Assertions.assertThat(member).isEqualTo(result);
        //static import 시 assertThat 으로 바로 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
    
    //테스트 순서는 보장이 안됨
    //findAll 하면 repository에는 spring1, spring2 저장됨
    //그 상태에서 findByName 수행하면 다른 결과가 나올 수 있음
    //테스트 끝나면 clear 해주도록 하자 (AfterEach에서 clear 호출해주기)


    //참고) TDD : 테스트 주도 개발 (테스트를 먼저 정의하고 구현을 하는 방법)
}
