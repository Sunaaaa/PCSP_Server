
use pcsp;

-- 로그인 
select * from user where userid = 'joo' and userpw = 'joo';
select * from user where userid = ? and userpw = ? ;

-- 등록된 차동차 목록
select * from car;

-- 위도 경도 이용해서 본인 위치와 가까운 차량 목록 출력
select * from car where carLong BETWEEN 



