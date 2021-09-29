package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//어떤 데이터 구조(ex. jdbc, nosql 등)를 붙일지 모르므로 인터페이스로 구현
public interface MemberRepository {
    Member save(Member member); //멤버 저장용
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    /*
        findById, findByName의 return이 null일 수도 있다.
        이 때, null 대신 optional로 감싸서 반환하는 방법을 많이 선호 (java8 부터 등장)
     */
    List<Member> findAll();

    //interface 만든 후 구현체를 만들어줘야함

}
