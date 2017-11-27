INSERT INTO role (`id`, `type`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `budget_db`.`user` (`id`,`cash`,`Enabled`,`mail`,`Password`,`Username`)
values (1,0,1,'user@mail.com','password','user');
INSERT INTO user_roles (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO role (`id`, `type`) VALUES ('2', 'ROLE_USER');