
DROP table   `contenttube`.`img_user`;
CREATE TABLE `contenttube`.`img_user` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45),
  `lname` VARCHAR(45),
  `address` VARCHAR(200),
  `city` VARCHAR(50),
  `isadmin` BOOLEAN,
  `image_url` VARCHAR(200),
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;

ALTER TABLE `contenttube`.`img_user` ADD COLUMN `create_time` DATE AFTER `image_url`,
 ADD COLUMN `modified_time` DATETIME NOT NULL AFTER `create_time`;

ALTER TABLE `contenttube`.`img_user` ADD COLUMN `email_id` VARCHAR(200) AFTER `modified_time`,
 ADD COLUMN `password` VARCHAR(50) AFTER `email_id`;

 ALTER TABLE `contenttube`.`img_user` MODIFY COLUMN `isadmin` SMALLINT(5) UNSIGNED;


DROP table   `contenttube`.`img_user_to_friend`;
CREATE TABLE `contenttube`.`img_user_to_friend` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `friend_id` INTEGER UNSIGNED NOT NULL,
  `isaccepted` BOOLEAN NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;

ALTER TABLE `contenttube`.`img_user_to_friend` ADD COLUMN `create_time` DATE NOT NULL AFTER `isaccepted`,
 ADD COLUMN `modified_time` DATETIME NOT NULL AFTER `create_time`;

 ALTER TABLE `contenttube`.`img_user_to_friend` MODIFY COLUMN `isaccepted` SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0;


DROP table   `contenttube`.`img_role`;
 CREATE TABLE `contenttube`.`img_role` (
   `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   `index` INTEGER UNSIGNED NOT NULL,
   `role_name` VARCHAR(100) NOT NULL,
   `bit_map` INTEGER UNSIGNED,
   PRIMARY KEY(`id`)
 )
 ENGINE = InnoDB;


DROP table   `contenttube`.`img_operation`;
 CREATE TABLE `contenttube`.`img_operation` (
   `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   `operation_name` VARCHAR(100),
   `bit_map` VARCHAR(45),
   PRIMARY KEY(`id`)
 )
 ENGINE = InnoDB;

 ALTER TABLE `contenttube`.`img_operation` MODIFY COLUMN `bit_map` INTEGER UNSIGNED,
  ADD COLUMN `display_name` VARCHAR(100) AFTER `bit_map`;







