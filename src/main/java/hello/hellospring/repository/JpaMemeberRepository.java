package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemeberRepository implements MemberRepository{

    private final EntityManager em; //jpo는 entity manager로 모든 것이 동작
    //build.gradle에서 data-jpa 라이브러리 받고 나면 spring boot는 자동으로 entity manager를 생성해줌
    //entitymanager는 jdbc template에서 썼던 datasource도 내부적으로 가지고 있음

    public JpaMemeberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist영구 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //Member.class로 조회
        //set parameter로 조회 파라미터 셋팅
        List<Member> result = em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name).getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
        //select 시 Member 객체 자체를 조회(Member의 앨리어스인 m 써서)
    }
}
