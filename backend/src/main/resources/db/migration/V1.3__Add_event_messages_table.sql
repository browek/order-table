CREATE TABLE `event_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `title` VARCHAR(256) NOT NULL,
  `message` VARCHAR(768) NOT NULL,
  `date_and_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_message_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_event_message_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);