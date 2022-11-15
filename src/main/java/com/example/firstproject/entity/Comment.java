package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 생성
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 처리
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패: 댓글의 id가 없어야 함");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패: 게시글의 id가 잘못됐습니다");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}
