package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface에서 interface 받을 때는 implements가 아닌 extends
//spring data jpa는 JpaRepository를 받고 있으면 구현체를 자동으로 만들어줌(스프링빈에 자동으로 등록)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    //JPOL select m from Member m where m.name = ?
    //규칙에 따라 쿼리가 생성됨
    //findBy[Name] => name으로 db에서 조회
    //findByNameAndId(String name, Long id) => 이에 맞는 쿼리를 생성해줌
    @Override
    Optional<Member> findByName(String name);

}
