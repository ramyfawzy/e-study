-- CREATE SCHEMA estudy;
SET REFERENTIAL_INTEGRITY FALSE;
 -- DROP TABLE IF EXISTS tutorial ;
 CREATE TABLE tutorial(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT, 
 description VARCHAR(255), 
 published BOOLEAN, 
 title VARCHAR(255)
 );
 -- DROP TABLE IF EXISTS student ;
 CREATE TABLE student(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT, 
 name VARCHAR(255)
 );
 
 -- DROP TABLE IF EXISTS tutorial_student ;
 CREATE TABLE tutorial_student (
  tutorial_id bigint(20) NOT NULL,
  student_id bigint(20) NOT NULL,
  PRIMARY KEY (`tutorial_id`,`student_id`),
  foreign key (`tutorial_id`) REFERENCES tutorial (`id`),
  foreign key (`student_id`) REFERENCES student (`id`)
);
SET REFERENTIAL_INTEGRITY TRUE;