package hello.hellospring.controller;

public class MemberForm {
    private String name; //이 name과 members/createMemberForm의 name이 매칭될 것임
    //form에서 input name = "name"으로 설정한 부분이 위 변수에 매핑됨
    //spring에서 setName을 호출해서 form에서 넘어온 값을 셋팅해줌

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
