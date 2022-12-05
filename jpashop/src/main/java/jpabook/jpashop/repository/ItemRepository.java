package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품저장
    //item 은 처음에 저장될 때는 아이디가 없음 - jpa 가 제공하는 persist 를 사용하여 추가
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // 새로 생성하는 상품 - 신규 등록
        } else {
            em.merge(item); // update 와 유사함(db 에 등록된 것을 가져와서 작업)
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
