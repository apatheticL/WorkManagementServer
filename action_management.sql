CREATE SCHEMA IF NOT EXISTS `action_management` DEFAULT CHARACTER SET utf8 ;
USE `action_management` ;

CREATE TABLE IF NOT EXISTS `action_management`.`user_profile` (
  `profile_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `full_name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `avatar` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `addres` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `phone_number` VARCHAR(15) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `email` VARCHAR(50) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`profile_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`team` (
  `group_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_name` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`user_team` (
  `group_id` INT(11) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`group_id`, `profile_id`),
  INDEX `fk_group_user_2_idx` (`profile_id` ASC),
  CONSTRAINT `fk_user_group_1`
    FOREIGN KEY (`group_id`)
    REFERENCES `action_management`.`team` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_group_2`
    FOREIGN KEY (`profile_id`)
    REFERENCES `action_management`.`user_profile` (`profile_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`action` (
  `action_id` INT(11) NOT NULL AUTO_INCREMENT,
  `action_name` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `profile_id` INT(11) NOT NULL,
  `group_id` INT(11) NOT NULL,
  `time_start` DATE NULL DEFAULT NULL,
  `time_end` DATE NULL DEFAULT NULL,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `action_status` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `description` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`action_id`),
  CONSTRAINT `fk_group_id`
    FOREIGN KEY (`group_id`)
    REFERENCES `action_management`.`team` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_create`
    FOREIGN KEY (`profile_id`)
    REFERENCES `action_management`.`user_profile` (`profile_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `action_management`.`action_small` (
  `action_small_id` INT(11) NOT NULL AUTO_INCREMENT,
  `action_id` INT(11) NOT NULL,
  `description` LONGTEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`action_small_id`),
  CONSTRAINT `fk_action_small_1`
    FOREIGN KEY (`action_id`)
    REFERENCES `action_management`.`action` (`action_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`user_action_small` (
	`user_action_small_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_id` INT(11) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  `action_small_id` INT(11) NOT NULL,
  `part` LONGTEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `time_start` DATE NULL DEFAULT NULL,
  `time_end` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`user_action_small_id`),
   INDEX `fk_user_action_small_2_idx` (`group_id` ASC, `profile_id` ASC),
  CONSTRAINT `fk_user_action_small_1`
    FOREIGN KEY (`action_small_id`)
    REFERENCES `action_management`.`action_small`(`action_small_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_action_small_2`
    FOREIGN KEY (`group_id` , `profile_id`)
    REFERENCES `action_management`.`user_team` (`group_id` , `profile_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`user_action_report` (
  `report_id` INT(11) NOT NULL  AUTO_INCREMENT,
  `user_action_small_id` INT(11) NOT NULL,
  `action_id` INT(11) NOT NULL,
  `action_actual` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `action_next` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `action_issua` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `time_report` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`report_id`),
  CONSTRAINT `fk_user_action_report_1`
    FOREIGN KEY (`user_action_small_id`)
    REFERENCES `action_management`.`user_action_small` (`user_action_small_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `fk_user_action_report_4`
    FOREIGN KEY (`action_id`)
    REFERENCES `action_management`.`action` (`action_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`comment_action` (
  `comment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `profile_id` INT(11) NOT NULL,
  `group_id` INT(11) NOT NULL,
  `action_id` INT(11) NOT NULL,
  `content` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `type_content` INT,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
   CONSTRAINT `fk_group_id_comment`
    FOREIGN KEY (`group_id`)
    REFERENCES `action_management`.`user_team` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_id_comment_2`
    FOREIGN KEY (`group_id` , `profile_id`)
    REFERENCES `action_management`.`user_team` (`group_id` , `profile_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `action_management`.`invitation_friend` (
  `friend_id` INT(11) NOT NULL AUTO_INCREMENT,
  `sender_id` INT(11) NOT NULL,
  `receiver_id` INT(11) NOT NULL,
  `is_accept` INT(11) NULL DEFAULT NULL,
  `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`friend_id`),
   INDEX `fk_invitation_friend_1_idx` (`sender_id` ASC),
 INDEX `fk_invitation_friend_2_idx` (`receiver_id` ASC),
 CONSTRAINT `fk_invitation_friend_1`
  FOREIGN KEY (`sender_id`)
  REFERENCES `action_management`.`user_profile` (`profile_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
 CONSTRAINT `fk_invitation_friend_2`
  FOREIGN KEY (`receiver_id`)
  REFERENCES `action_management`.`user_profile` (`profile_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- insert profile

insert into user_profile values (default,'nhatle','1234','Nhat Le','https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg','Thanh Hoa','01232343','le1232@gmail.com',default);
insert into user_profile values (default,'thuphuong','1234','Thu Phuong','https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg','Thanh Hoa','01236564','lephuong1232@gmail.com',default);
insert into user_profile values (default,'sonson','1234','Son Son','https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg','Thanh Hoa','012534553','leson1232@gmail.com',default);
insert into user_profile values (default,'hoanghanh','1234','Hoang Hanh','https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg','Thanh Hoa','01236564','lephuong1232@gmail.com',default);
insert into user_profile values (default,'thutra','1234','Thu Tra','https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg','Thanh Hoa','012534553','leson1232@gmail.com',default);

-- insert group
insert into action_management.team values (default,'BTL Win',default);
insert into action_management.team values (default,'BTL Java',default);
insert into action_management.team values (default,'BTL XML',default);
insert into action_management.team values (default,'BTL Công nghệ đa phương tiện',default);
insert into action_management.team values (default,'BTL web',default);

-- insert user_group
insert into action_management.user_group values (4,7);
insert into action_management.user_group values (4,8);
insert into action_management.user_group values (4,9);
insert into action_management.user_group values (5,9);
insert into action_management.user_group values (5,7);
insert into action_management.user_group values (7,9);
insert into action_management.user_group values (7,8);
insert into action_management.user_group values (7,10);
insert into action_management.user_group values (8,11);
insert into action_management.user_group values (8,10);
insert into action_management.user_group values (9,10);
insert into action_management.user_group values (9,11);
insert into action_management.user_group values (9,7);
insert into action_management.user_group values (9,8);
insert into action_management.user_group values (9,9);

-- insert commtent_action
insert into comment_action values (default,7,4,10,'chuc nang a',1,default);
insert into comment_action values (default,8,4,10,'chuc nang a',1,default);
insert into comment_action values (default,7,4,10,'https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg',2,default);
insert into comment_action values (default,9,5,10,'chuc nang b',1,default);
insert into comment_action values (default,7,5,11,'chuc nang a',1,default);
insert into comment_action values (default,9,5,11,'https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg',2,default);
insert into comment_action values (default,8,5,11,'chuc nang a',1,default);
insert into comment_action values (default,8,7,12,'chuc nang a',1,default);
insert into comment_action values (default,9,7,12,'chuc nang a',1,default);
insert into comment_action values (default,10,7,12,'https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg',2,default);
insert into comment_action values (default,10,8,13,'chuc nang a',1,default);
insert into comment_action values (default,11,8,13,'chuc nang a',1,default);
insert into comment_action values (default,7,9,14,'chuc nang a',1,default);
insert into comment_action values (default,8,9,14,'https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg',2,default);
insert into comment_action values (default,9,9,14,'chuc nang a',1,default);

-- insert action
insert into action_management.action values (default,'bài tập lớn win',7,4,'2019-02-12','2019-12-12',default);
insert into action_management.action values (default,'bài tập lớn Java',9,5,'2019-02-12','2019-12-12',default);
insert into action_management.action values (default,'bài tập lớn XML',9,7,'2019-02-12','2019-12-12',default);
insert into action_management.action values (default,'bài tập lớn CNDPT',11,8,'2019-02-12','2019-12-12',default);
insert into action_management.action values (default,'bài tập lớn WEB',8,9,'2019-02-12','2019-12-12',default);
 -- insert action small
 
 insert into action_management.action_small values (default, 10,'khảo sát hệ thống');
 insert into action_management.action_small values (default, 10,'Phân tích thiết kế hệ thống');
 insert into action_management.action_small values (default, 10,'code');
 insert into action_management.action_small values (default, 10,'kiểm thử');
 insert into action_management.action_small values (default, 11,'khảo sát hệ thống');
 insert into action_management.action_small values (default, 11,'phân tích thiết kế hệ thống');
 insert into action_management.action_small values (default, 11,'code');
 insert into action_management.action_small values (default, 11,'kiểm thử');
 insert into action_management.action_small values (default, 12,'tìm tài liệu');
 insert into action_management.action_small values (default, 12,'phân tích bài toán');
 insert into action_management.action_small values (default, 12,'triển khai bài toán');
 insert into action_management.action_small values (default, 12,'báo cáo');
 insert into action_management.action_small values (default, 13,'tìm tài liệu');
 insert into action_management.action_small values (default, 13,'phân tích bài toán');
 insert into action_management.action_small values (default, 13,'triển khai bài toán');
 insert into action_management.action_small values (default, 13,'báo cáo');
 insert into action_management.action_small values (default, 14,'khảo sát hệ thống');
 insert into action_management.action_small values (default, 14,'Phân tích thiết kế hệ thống');
 insert into action_management.action_small values (default, 14,'code');
 insert into action_management.action_small values (default, 14,'kiểm thử');
 
 -- insert user action small
 insert into action_management.user_action_small values (default,4,7,1,'thanh vien thuc hien','2019-02-12','2019-02-22');
 insert into action_management.user_action_small values (default,4,8,2,'thanh vien thuc hien','2019-02-22','2019-03-12');
 insert into action_management.user_action_small values (default,4,9,3,'thanh vien thuc hien','2019-03-12','2019-05-22');
 insert into action_management.user_action_small values (default,4,7,3,'thanh vien thuc hien','2019-03-12','2019-05-22');
 insert into action_management.user_action_small values (default,4,8,4,'thanh vien thuc hien','2019-05-12','2019-06-22');
 insert into action_management.user_action_small values (default,5,7,5,'thanh vien thuc hien','2019-02-12','2019-02-22');
 insert into action_management.user_action_small values (default,5,7,7,'thanh vien thuc hien','2019-02-22','2019-04-22');
 insert into action_management.user_action_small values (default,5,9,6,'thanh vien thuc hien','2019-02-22','2019-03-12');
 insert into action_management.user_action_small values (default,5,7,8,'thanh vien thuc hien','2019-05-12','2019-06-22');
 insert into action_management.user_action_small values (default,7,8,9,'thanh vien thuc hien','2019-02-12','2019-02-22');
 insert into action_management.user_action_small values (default,8,11,13,'thanh vien thuc hien','2019-02-12','2019-02-22');
 insert into action_management.user_action_small values (default,9,10,17,'thanh vien thuc hien','2019-02-12','2019-02-22');
 
 -- insert user action report
 insert into action_management.user_action_report values (default,1,10,'hoan thanh','code','no',default);
 insert into action_management.user_action_report values (default,2,10,'hoan thanh','bao cao','no',default);
 insert into action_management.user_action_report values (default,3,10,'hoan thanh','no','no',default);
 insert into action_management.user_action_report values (default,6,11,'hoan thanh','no','no',default);
 insert into action_management.user_action_report values (default,7,11,'hoan thanh','no','no',default);
 insert into action_management.user_action_report values (default,10,12,'hoan thanh','no','no',default);
 insert into action_management.user_action_report values (default,11,13,'hoan thanh','no','no',default);
 insert into action_management.user_action_report values (default,12,14,'hoan thanh','no','no',default);
 
 -- insert comment
insert into action_management.comment_action values (default, 7,4,10,'ahihi',1,default);
insert into action_management.comment_action values (default, 7,5,11,'ahihi',1,default);
insert into action_management.comment_action values (default, 8,7,12,'ahihi',1,default);
insert into action_management.comment_action values (default, 10,8,13,'ahihi',1,default);
insert into action_management.comment_action values (default, 11,9,14,'ahihi',1,default);
 -- insert invitation friend
 insert into action_management.invitation_friend values (default,7,8,1,default);
 insert into action_management.invitation_friend values (default,7,9,1,default);
 insert into action_management.invitation_friend values (default,7,10,1,default);
 insert into action_management.invitation_friend values (default,8,9,1,default);
 insert into action_management.invitation_friend values (default,8,10,1,default);
 insert into action_management.invitation_friend values (default,8,11,0,default);
 insert into action_management.invitation_friend values (default,9,11,0,default);
 insert into action_management.invitation_friend values (default,9,10,0,default);
 
 select * from action_management.user_profile;
 select * from action_management.action;
 select * from action_management.action_small;
 select * from action_management.comment_action;
 select * from action_management.team;
 select * from action_management.user_team;
 select * from action_management.invitation_friend;
 select * from action_management.user_action_report;
 select * from action_management.user_action_small;