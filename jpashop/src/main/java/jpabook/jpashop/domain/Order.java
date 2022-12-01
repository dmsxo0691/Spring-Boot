package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래 키가 member_id
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 자바에서 자체 제공하는 시간 객체

    @Enumerated(EnumType.STRING) // 상태 현황은 항상 String 타입으로 하는 게 좋음
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]
}
