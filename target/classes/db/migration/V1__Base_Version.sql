CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mounthly_limit` decimal(15,0) NOT NULL,
  `Enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKej3jidxlte0r8flpavhiso3g6` (`role_id`),
  CONSTRAINT `FK7ov27fyo7ebsvada1ej7qkphl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKej3jidxlte0r8flpavhiso3g6` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `card_number` varchar(45) NOT NULL,
  `cash` decimal(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cards_user1` (`user_id`),
  CONSTRAINT `fk_cards_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `places` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `planned_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `categories_id` bigint(20) NOT NULL,
  `places_id` bigint(20) DEFAULT NULL,
  `cards_id` bigint(20) DEFAULT NULL,
  `startDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paymentDayPos` int(11) NOT NULL,
  `sum` float NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_planned_records_categories1_idx` (`categories_id`),
  KEY `fk_planned_records_places1_idx` (`places_id`),
  KEY `fk_planned_records_cards1_idx` (`cards_id`),
  KEY `fk_planned_records_user1` (`user_id`),
  CONSTRAINT `fk_planned_records_cards1` FOREIGN KEY (`cards_id`) REFERENCES `cards` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_planned_records_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_planned_records_places1` FOREIGN KEY (`places_id`) REFERENCES `places` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_planned_records_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `records` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `cards_id` bigint(20) DEFAULT NULL,
  `places_id` bigint(20) DEFAULT NULL,
  `sum` float NOT NULL,
  `categories_id` bigint(20) NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_orders_cards1_idx` (`cards_id`),
  KEY `fk_orders_places1_idx` (`places_id`),
  KEY `fk_records_categories1_idx` (`categories_id`),
  KEY `fk_orders_user1` (`user_id`),
  CONSTRAINT `fk_orders_cards1` FOREIGN KEY (`cards_id`) REFERENCES `cards` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_places1` FOREIGN KEY (`places_id`) REFERENCES `places` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_records_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
