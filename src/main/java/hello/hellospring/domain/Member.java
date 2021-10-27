package hello.hellospring.domain;

import javax.persistence.*;

@Entity //Entity 어노테이션 통해 jpa가 관리하는 entity로 만든다
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 선언. db가 알아서 생성해주도록? strategy 설정. id컬럼이 자동 생성이라 그런 듯
    private Long id;

    //@Column(name="username") //db 컬럼과 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
