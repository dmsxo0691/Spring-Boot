package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //외래키가 member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //자바에서 주문시간 제공

    @Enumerated(EnumType.STRING) //String타입으로 항상 설정
    private OrderStatus status;  //주문상태  [ORDER , CANCEL]

    // == 연관관계 메서드 == //
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
//연관과계 메서드를 통해 관련된 내용들을 한번에 처리해 주는 것이
//외부에서 Setter에 접근하게 하는 것 보다 좋다.

    // 복잡한 생성은 생성메서드를 만들어서 사용 - 주문엔티티 생성
    //생성 메서드 - 주문회원, 배송정보, 주문 상문의 정보를 받아서 실제 주문 엔티니를 생성
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems)
            order.addOrderItem(orderItem);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    //==비즈니스 로직==//

    /**
     * 주문 취소
     */
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP)
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");

        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems)
            orderItem.cancel();
        //jpa의 장점중 하나로 order에서 cancel()을 사용하게 되면 따로 값의 변경이 있지만
        //따로 update코드로 작성하지 않습니다.
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems)
            totalPrice += orderItem.getTotalPrice();

        return totalPrice;
    }
}







