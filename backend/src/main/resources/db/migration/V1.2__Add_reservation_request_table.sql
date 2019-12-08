CREATE TABLE `reservation_request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asking_user_id` INT NOT NULL,
  `reciving_user_id` INT NOT NULL,
  `numer_of_persons` INT NOT NULL DEFAULT 0,
  `date_and_time` DATETIME NOT NULL,
  `message` VARCHAR(512) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_request_user_idx` (`asking_user_id` ASC),
  INDEX `fk_reservation_request_user1_idx` (`reciving_user_id` ASC),
  CONSTRAINT `fk_reservation_request_user`
  FOREIGN KEY (`asking_user_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_request_user1`
  FOREIGN KEY (`reciving_user_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);