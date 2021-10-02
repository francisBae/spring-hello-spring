package hello.hellospring.repository;
//import hello.hellospring.domain.Member;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //저장할 공간
    //실무에서는 동시성 문제가 있으므로 공유되는 변수는 concurrent hashmap 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이라도 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //루프로 돌리는 것.. 람다
                .filter(member -> member.getName().equals(name)) //->는 람다 표현
                .findAny();
        //name으로 받은 것과 member.getName이 같은것만 반환
        //findAny는 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 멤버들을 반환
    }

    //데이터 clear용 메소드 정의
    public void clearStore(){
        store.clear();
    }
}
