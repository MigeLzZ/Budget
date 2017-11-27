INSERT INTO `budget_db`.`role` (`id`, `type`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `budget_db`.`role` (`id`, `type`) VALUES ('2', 'ROLE_USER');
insert into `budget_db`.`user` (`id`,`username`, `mail`, `password`, `create_time`,  `mounthly_limit`, `enabled`)
values (1,'user', 'user@mail.com', 'password',NOW(), 0, 1);
INSERT INTO `budget_db`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');