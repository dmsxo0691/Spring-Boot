package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 어딘가에 내장 할 수 있음
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
// 식별자가 없고 값만 있으므로 변경 시 추적 불가
// 1. Setter 제거
// 2. 생성자 값을 모두 초기화해서 변경 불가능한 클래스를 만듦
// 3. public 보다는 protected 로 설정하여 보안