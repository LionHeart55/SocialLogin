USE `myapp`;

INSERT INTO role (`id`,`name`) VALUES (1,'ROLE_USER');
INSERT INTO role (`id`,`name`) VALUES (2,'ROLE_ADMIN');

INSERT INTO user (user_id,name,email_id,password,provider,active) VALUES
('someuser','Some User','someuser@gmail.com','pass','NONE',1);

INSERT INTO user_role (user_id,role_id) VALUES (1,1);
