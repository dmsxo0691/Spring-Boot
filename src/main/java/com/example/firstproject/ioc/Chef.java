package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    // 셰프는 식재료 공장을 알고 있음
    private  IngredientFactory ingredientFactory;

    // 셰프가 식재료 공장과 협업하기 위한 DI(의존성 주입: 동작에 필요한 객체를 외부에서 받아오는 것)
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {

        // 문제 1 : 식재료와 셰프의 의존성이 큼
        // 해결 : 셰프와 식재료 사이에 조달 공정을 두어 의존성을 낮춤
        //       요구사항 변경에 유연하도록 코드 개선

        //재료 준비
      //  Pork pork = new Pork("한돈 등심");
//        Beef beef = new Beef("한우 꽃등심");
        Ingredient ingredient = ingredientFactory.get(menu);

        //요리 반환
        return ingredient.getName() + "으로 만든 " + menu;

        // 두번째 방법
        // 외부에서 값을 삽입 받는 방법 -> DI 방식으로 코드 개선
        // 요구사항이 변하더라도 내부 코드가 변하지 않도록 유연한 대처가 가능한 코드 작성
        // ioc 컨테이너에 필요한 객체를 등록(Component)하고, Autowired 를 통해 가져올 수 있다.
    }
}
