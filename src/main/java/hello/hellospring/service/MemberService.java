package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//domain과 repository를 이용해 서비스 구현
//서비스는 비즈니스에서 가져온 용어들로 사용(의사소통의 원활함 위해)
//서비스는 비즈니스 의존적으로 설계하고 repository는 개발 용어에 가깝게

@Transactional //jpa는 transactional과 항상 붙어다녀야 함 / Transactional을 클래스에 걸어도 되고 대상 메소드에 걸어도 됨
public class MemberService {
    //ctrl+shift+T 로 테스트 생성

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();

    //직접 생성이 아니라 외부에서 넣어주도록 바꿔주기 (DI : Dependency Injection)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
        public Long join(Member member){



            //같은 이름이 있는 중복 회원X
            //        Optional<Member> result = memberRepository.findByName(member.getName());//끝에서 ctrl + alt + v
            //        result.ifPresent(m->{
            //            throw new IllegalStateException("이미 존재하는 회원입니다.");
            //        }); //ifPresent:값이 있다면

            validateDuplicateMember(member);
            //ctrl+shift+alt+T 후 extract method로 위 내용을 함수로 뽑아낸다(자주 호출될 예정이므로)
            //extract method 바로 하려면 ctrl+shift+m

            //        result.orElseGet("~~~") //값이 있으면 꺼내고 없으면 디폴트값 꺼냄

            //optional 안에 멤버객체가 있음. optional로 감싸면 위의 ifPresent 같이 여러 메소드 사용 가능
            //null이 있는 경우 optional로 감싸서 반환되면 ifPresent 통해 식별 가능

            memberRepository.save(member);
            return member.getId();

        }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //findByName의 return형이 optional이라 바로 메소드 사용 가능
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
