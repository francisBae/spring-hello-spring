package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //Test는 과감하게 한글명으로 바꿔도 됨(ex. join->회원가입)
    //테스트는 영어권 사람과 일하는게 아니면 한글로도 많이 씀

    /*test는 아래 패턴으로 작성하면 좋다
    given (뭔가 주어짐)
    when (이걸 실행했을 때)
    then (결과가 이렇게 나와야 함)
    */
    MemberService memberService;
    MemoryMemberRepository memberRepository; //clear 위해

     @BeforeEach
     public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
     }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //테스트 수행 끝날때마다 비워주기
    }

    @Test
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

        /*
        memberService.join(member1);
        try{
            memberService.join(member2); //중복 발생하도록 한다
            fail("예외가 발생해야 합니다.");
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}