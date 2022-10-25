/*
 * BOARD 테이블과 community_member을 이용해서 
 	제목이 '공부'이거나 '제목'인  게시물 조회
 */
delete from board

INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부', '월요일 즐겁네요', sysdate, 'java');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부2', '월요일 기쁘네요', sysdate, 'spring');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부3', '월요일 행복해요', sysdate, 'java');
<<<<<<< HEAD
/*INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목', '내용1', sysdate, 'python');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목입니다', '내용2', sysdate, 'mybatis');*/
=======
<<<<<<< HEAD

INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목', '내용3', sysdate, 'spring');

INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목', '내용1', sysdate, 'spring');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목입니다', '내용2', sysdate, 'java');
>>>>>>> branch 'main' of https://github.com/Ha-sen-se/20221024hyl.git
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부3', '내용3', sysdate, 'spring');


select * from board
select * from community_member

--하영
SELECT b.no,b.title,b.content,b.time_posted,c.id 
FROM board b
INNER JOIN community_member c ON b.id=c.id
WHERE b.title='공부' OR b.title='제목';


--윤수
SELECT b.no,b.title,b.content,b.time_posted,c.id 
FROM board b
INNER JOIN community_member c ON b.id=c.id
WHERE b.title='공부' OR b.title='제목';


SELECT * FROM BOARD WHERE TITEL='공부';

SELECT b,no,b.title,b.content
FROM	community_member m
INNER JOIN 	board b ON b.id=m.id
WHERE title IN('공부','제목')

/*oracle join*/
SELECT b.no, b.title, b.content, b.time_posted, b.id
FROM board b JOIN community_member cm
ON b.id=cm.id
where b.title in('공부', '제목');

/*ANSI SQL*/
SELECT b.no, b.title, b.content, b.time_posted, b.id
FROM board b
INNER JOIN	community_member cm ON b.id = cm.id --결합할 테이블을 JOIN 뒤에
where b.title ='공부' or b.title= '제목';

SELECT b.no, b.title, b.content, b.time_posted, b.id
FROM board b
INNER JOIN	community_member cm ON b.id = cm.id --결합할 테이블을 JOIN 뒤에
where b.title in('공부','제목');

