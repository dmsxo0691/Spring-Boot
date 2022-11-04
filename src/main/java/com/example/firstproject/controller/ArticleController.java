package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //loging을 위한 롬복 어노테이션
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해놓은 레퍼지토리 객체를 호출(DI:의존성 주입)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        //System.out.println(form.toString());
        log.info(form.toString());

        //1. dto를 Entity 방식으로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2. Repository에게 Entity 를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}") //해당 url요청을 처리 선언
    public String show(@PathVariable Long id, Model model){ // 해당 url에서 id 변수를 가져옴
        log.info("id = " + id);
        // 1. id로 데이터 호출
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 호출한 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }
}
