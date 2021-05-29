 set foreign_key_checks = 0;
 DROP TABLE IF EXISTS tutorial ;
 CREATE TABLE tutorial(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT, 
 description VARCHAR(255), 
 published BOOLEAN, 
 title VARCHAR(255)
 );
 DROP TABLE IF EXISTS student ;
 CREATE TABLE student(
 id bigint(20) PRIMARY KEY AUTO_INCREMENT, 
 name VARCHAR(255)
 );
 
 DROP TABLE IF EXISTS tutorial_student ;
 CREATE TABLE tutorial_student (
  tutorial_id bigint(20) NOT NULL,
  student_id bigint(20) NOT NULL,
  PRIMARY KEY (`tutorial_id`,`student_id`),
  KEY `FKlve19rmq1qxaru813t8p4nixl` (`student_id`),
  KEY `FKk9ip5gyk2ohv8pd7nugxdpmx7` (`tutorial_id`),
  CONSTRAINT `FKk9ip5gyk2ohv8pd7nugxdpmx7` FOREIGN KEY (`tutorial_id`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FKlve19rmq1qxaru813t8p4nixl` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
);
set foreign_key_checks = 1;