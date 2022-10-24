/*
 * BOARD 테이블과 community_member을 이용해서 
 	제목이 '공부'이거나 '제목'인  게시물 조회
 */
delete from board

INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부', '월요일 즐겁네요', sysdate, 'java');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부2', '월요일 기쁘네요', sysdate, 'spring');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부3', '월요일 행복해요', sysdate, 'java');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부3', '내용3', sysdate, 'spring');

select * from board


select  b.no, b.title, cm.name, to_char(time_posted, 'yyyy.mm.dd') as time_posted, b.hits
from BOARD b
inner join community_member cm on b.id = cm.id
where b.title in ('공부', '제목');

