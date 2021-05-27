 DROP TABLE IF EXISTS tutorial ;
 CREATE TABLE tutorial(
 id int(11) PRIMARY KEY AUTO_INCREMENT, 
 description VARCHAR(255), 
 published BOOLEAN, 
 title VARCHAR(255)
 );