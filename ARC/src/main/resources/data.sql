CREATE DATABASE arc;

USE arc;

CREATE TABLE gds_mst ( gds_cd INT(10) NOT NULL, gds_nm VARCHAR(255) );
CREATE TABLE member ( mb_idx int UNSIGNED NOT NULL AUTO_INCREMENT, id VARCHAR(20) NOT NULL, name VARCHAR(20), pw VARCHAR(20), email VARCHAR(50), PRIMARY KEY (mb_idx) );
CREATE TABLE inv_gds_lst ( igl_idx INT UNSIGNED NOT NULL AUTO_INCREMENT, gds_cd INT(10), prf_rto FLOAT(7,2), cms DOUBLE(10,2), PRIMARY KEY (igl_idx) );
CREATE TABLE my_inv_lst ( my_idx INT UNSIGNED NOT NULL AUTO_INCREMENT, id VARCHAR(20), gds_cd VARCHAR(20), inv_prod INT(10), my_inv_prc DOUBLE(10,2), PRIMARY KEY (my_idx) );
CREATE TABLE mb_rpy (rpy_idx INT unsigned NOT NULL AUTO_INCREMENT, mb_idx INT unsigned NOT NULL, prt_idx INT unsigned, content TEXT, rpy_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY (rpy_idx));


INSERT INTO member(id, name, pw, email) VALUES ('kimId', 'kim', '1234', 'user@example.com');
INSERT INTO gds_mst (gds_cd, gds_nm) VALUES (1, 'sangpum1');
INSERT INTO gds_mst (gds_cd, gds_nm) VALUES (1, 'sangpum2');
INSERT INTO gds_mst (gds_cd, gds_nm) VALUES (1, 'sangpum3');
INSERT INTO inv_gds_lst (gds_cd, prf_rto, cms) VALUES (1, 10.2, 20);
INSERT INTO inv_gds_lst (gds_cd, prf_rto, cms) VALUES (2, 13, 30);
INSERT INTO inv_gds_lst (gds_cd, prf_rto, cms) VALUES (3, 20, 80);
INSERT INTO my_inv_lst (id, gds_cd, inv_prod, my_inv_prc) VALUES ('kimId', 1, 10, 1000);
INSERT INTO mb_rpy (mb_idx, prt_idx, content) VALUES(1, null, "안녕하세요");
INSERT INTO mb_rpy (mb_idx, prt_idx, content) VALUES(1, null, "반갑습니다");


DROP DATABASE arc;