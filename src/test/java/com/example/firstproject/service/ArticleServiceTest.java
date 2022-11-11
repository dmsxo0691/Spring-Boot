package com.example.firstproject.service;

import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "갘갘갘", "111");
        Article b = new Article(2L, "엌", "22");
        Article c = new Article(3L, "읔", "23");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "갘갘갘", "1111");

        //실제
        Article article = articleService.show(id);

        //검증
        assertEquals(expected.toString(), article.toString());

    }
    @Test
    void show_실패____존재하지_않는_id_입력() {
        //예상
        Long id = 4L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);

        //검증
        assertEquals(expected.toString(), article.toString());
    }
}