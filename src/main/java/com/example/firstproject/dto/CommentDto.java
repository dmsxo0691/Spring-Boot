package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
//        static(클래스 메소드 선언에 사용) 은 CommentDto 를 생성하는 데 사용
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                // Article()의 id만 필요하므로
                comment.getNickname(),
                comment.getBody()
        );
    }
}
