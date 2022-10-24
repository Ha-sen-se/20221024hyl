/*
 * BOARD 테이블과 community_member을 이용해서 
 * 단, 직업(job)이 CLERK인 사원이거나 Manager인 사원만 조회 제목이 '공부'이거나 '제목'인  게시물 조회
 */

INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목', '내용1', sysdate, 'python');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '제목입니다', '내용2', sysdate, 'mybatis');
INSERT INTO BOARD(no, title, content, time_posted, id) VALUES(board_seq.nextval, '공부3', '내용3', sysdate, 'spring');


