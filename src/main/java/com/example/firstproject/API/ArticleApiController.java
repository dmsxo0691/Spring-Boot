package com.example.firstproject.API;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //RestAPI 용 컨트롤러 - 주로 데이터(JSON)를 반환
public class ArticleApiController {
    @Autowired //DI(dependency injection)
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        // 수정용 Entity 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {} ", id, article.toString());

        // 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리(대상이 없거나 id가 다른 경우)
        if(target == null || id != article.getId()){
            // 400 : 잘못된 요청 응답
            log.info("잘못된 요청 id: {}, article: {} ", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 200 : 업데이트 및 정상응답
        Article updated = articleRepository.save(article);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
}
