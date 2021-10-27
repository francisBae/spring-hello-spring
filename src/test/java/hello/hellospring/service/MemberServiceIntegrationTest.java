package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행한다
@Transactional //테스트 케이스에 이 어노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.
    //이렇게 하면  db에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

    //테스트 작성 시에는 필드에 injection 넣어주면 좋다
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //clear 위해



    //아래처럼 직접 생성하는게 아니고 스프링 컨테이너로부터 memberService, memberRepository 가져옴
//     @BeforeEach
//     public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//     }
// 메모리에 있는 데이터를 다음 테스트를 위해 비워주는 것
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore(); //테스트 수행 끝날때마다 비워주기
//    }

    @Test
    //@Commit : transactional 있어도 롤백 안하고 commit됨
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){ //저장 실패 케이스 테스트 위해
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //try catch 대신 위처럼 람다 사용해보기
        //()->코드 : 코드에 있는 로직 테울 때
        //결론은 memberService.join(member2)를 실행할건데 실패하면 IllegalStateException 띄워라

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}