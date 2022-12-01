package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함했다는 어노테이션
    private Address Address;

    @OneToMany(mappedBy = "member") // order 테이블의 member 필드에 의해 맵핑됐다.
    // 맵핑됨으로 인해 읽기 전용 변수가 됨
    private List<Order> orders = new ArrayList<>();

}
