insert into article(id, title, content) values (1, '갘갘갘', '111');
insert into article(id, title, content) values (2, '엌', '22');
insert into article(id, title, content) values (3, '읔', '23');

-- article 더미 데이터
insert into article(id, title, content) values (4, '당신의 인생영화는?', '댓글 ㄱ');
insert into article(id, title, content) values (5, '당신의 소울푸드는?', '댓글 ㄱㄱ');
insert into article(id, title, content) values (6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

-- comment 더미 데이터
-- 4번 게시글 기준
insert into comment(id, article_id, nickname, body) values (1, 4, 'Park', '굿 윌 헌팅');
insert into comment(id, article_id, nickname, body) values (2, 4, 'Kim', '아이 앰 샘');
insert into comment(id, article_id, nickname, body) values (3, 4, 'Choi', '쇼생크의 탈출');

-- 5번 게시글 기준
insert into comment(id, article_id, nickname, body) values (4, 5, 'Park', '치킨');
insert into comment(id, article_id, nickname, body) values (5, 5, 'Kim', '샤브샤브');
insert into comment(id, article_id, nickname, body) values (6, 5, 'Choi', '초밥');

-- 6번 게시글 기준
insert into comment(id, article_id, nickname, body) values (7, 6, 'Park', '조깅');
insert into comment(id, article_id, nickname, body) values (8, 6, 'Kim', '유튜브');
insert into comment(id, article_id, nickname, body) values (9, 6, 'Choi', '독서');








