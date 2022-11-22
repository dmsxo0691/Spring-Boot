package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    // article data도 가져와야 하므로 둘 다 불러옴

    public List<CommentDto> comments(Long articleId) {
        // 댓글 목록 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // Entity를 dto로 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        // 비어있는 dtos(arrayList) 하나하나를 for문으로 채워줌
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            // 클래스 방식을 호출
//            // createCommentDto(); 생성 메소드를 만들어서 사용
//            dtos.add(dto);
//        }
//        // 반환
//        return dtos;

        //stream 문법
        return commentRepository.findByArticleId(articleId)
                .stream()
                // stream으로 나열
                .map(comment -> CommentDto.createCommentDto(comment))
                // 하나씩 꺼내온 데이터를 맵핑, 이 comment를 CommentDto로 변환  .createCommentDto 생성 메소드를 통해서
                .collect(Collectors.toList());

    }
    @Transactional
    // 도중 예외 발생 시 롤백
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티 DB에 저장
        Comment created = commentRepository.save(comment);

        //Dto로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패"));

        // 댓글 수정
        target.patch(dto);

        // 수정된 댓글 DB 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);


    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패"));

        // 댓글 삭제
        commentRepository.delete(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
