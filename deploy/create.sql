CREATE SCHEMA myapp;

USE myapp;
  
 CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  user_id varchar(255) NOT NULL,
  name varchar(32) DEFAULT NULL,
  email_id varchar(128) DEFAULT NULL,
  password varchar(64) NOT NULL,
  provider varchar(32) NOT NULL,
  active int(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY fk_user (user_id)
);

CREATE TABLE role (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(32) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id,role_id),
  KEY fk_role (role_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (id)
);

