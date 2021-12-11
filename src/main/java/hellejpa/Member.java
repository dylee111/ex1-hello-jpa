package hellejpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA 처음 로딩될 때 JPA를 사용하는 객체로 인식하고 관리함.
//@Table(name = "USER") // 만약 DB의 테이블 이름이 USER로 되어있다면 @Table의 name 속성을 통해 매핑
public class Member {
    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
