package com.example.secondproject.service;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
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
        Article a = new Article(1L, "갘갘갘", "111");
        Article b = new Article(2L, "엌", "22");
        Article c = new Article(3L, "읔", "23");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        Article expected = new Article(id, "갘갘갘", "111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(),article.toString());
    }
    @Test
    void show_실패() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공() {
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패() {
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected,article);
    }


}