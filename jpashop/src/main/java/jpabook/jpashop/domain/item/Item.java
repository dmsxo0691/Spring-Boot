package jpabook.jpashop.domain.item;

// 구현체를 가지고 할 것이므로 추상 클래스로 제작

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {
    // 상속관계 전략을 지정 - 싱글테이블

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직 : 수량의 증감
    // addStock(), removeStock()
    // 상품 서비스에서 상품 엔티티의 stock을 컨트롤 하기보다는
    // 상품 엔티티에 비즈니스 로직을 만드는 것이 엔티티의 독립성을 지켜주기 좋다.

    // stock 증가: 파라미터로 넘어온 수만큼 재고를 늘림
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    // stock 감소: 파라미터로 넘어온 수보다 재고 수가 많은지 확인 후 재고를 줄인다.
    public void removeStock(int quantity){
       int restStock = this.stockQuantity - quantity;
       if(restStock < 0){
           throw new NotEnoughStockException("need more stock");
       }
       this.stockQuantity = restStock;
    }
}
