# ActualReturnCalculator
2018/10/8~10/13
게시판 만들기 - 실제 수익률을 계산해주는 게시판입니다.


## Database 구성 및 Table 구성
- arc 데이터베이스 생성
CREATE DATABASE arc;

- goods master 테이블 생성
CREATE TABLE gds_mst (
 gds_cd INT(10) NOT NULL,
 gds_nm VARCHAR(255)
);

- goods master 테이블 data sample
INSERT INTO gds_mst (gds_cd, gds_nm) VALUES (1, 'sangpum1');

- member 테이블 생성
CREATE TABLE member (
 mb_idx int UNSIGNED NOT NULL AUTO_INCREMENT,
 id VARCHAR(20) NOT NULL,
 name VARCHAR(20),
 pw VARCHAR(20),
 email VARCHAR(50),
 PRIMARY KEY (mb_idx)
);

- member 테이블 data sample
INSERT INTO member(id, name, pw, email) VALUES ('kimId', 'kim', '123', 'kim@a.com');

- investment good list 테이블 생성
CREATE TABLE inv_gds_lst (
    igl_idx INT UNSIGNED NOT NULL AUTO_INCREMENT,
    gds_cd INT(10),
    prf_rto FLOAT(7,2),
    cms DOUBLE(10,2), 
    PRIMARY KEY (igl_idx)
);

- investment good list 테이블 data sample
INSERT INTO inv_gds_lst (gds_cd, prf_rto, cms) VALUES (1, 10.2, 140);

- my investment list 테이블 생성
CREATE TABLE my_inv_lst (
    my_idx INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id VARCHAR(20),
    gds_cd VARCHAR(20),
    inv_prod INT(10),  --초기버전(개월수로)
    my_inv_prc DOUBLE(10,2),
    PRIMARY KEY (my_idx)
);

- my investment list 테이블 data sample
INSERT INTO my_inv_lst (id, gds_cd, inv_prod, my_inv_prc) VALUES ('kimId', 1, 10, 1000);

- mb_rpy 테이블 생성
CREATE TABLE mb_rpy (rpy_idx INT unsigned NOT NULL AUTO_INCREMENT, mb_idx INT unsigned NOT NULL, prt_idx INT unsigned, content TEXT, rpy_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY (rpy_idx));

- mb_rpy 테이블 data sample
INSERT INTO mb_rpy (mb_idx, prt_idx, content) VALUES(1, null, "안녕하세요");
