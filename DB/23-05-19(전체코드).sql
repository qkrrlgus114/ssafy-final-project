-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafit` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `ssafit`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`crew` (
  `crew_name` VARCHAR(50) NOT NULL,
  `crew_leader` VARCHAR(45) NOT NULL,
  `crew_id` INT NOT NULL AUTO_INCREMENT,
  `crew_create_date` DATETIME NULL DEFAULT NULL,
  `crew_content` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`crew_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`crew_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`crew_board` (
  `crew_board_id` INT NOT NULL AUTO_INCREMENT,
  `crew_board_title` VARCHAR(100) NOT NULL,
  `crew_crew_id` INT NOT NULL,
  PRIMARY KEY (`crew_board_id`),
  INDEX `fk_crew_board_crew1_idx` (`crew_crew_id` ASC) VISIBLE,
  CONSTRAINT `fk_crew_board_crew1`
    FOREIGN KEY (`crew_crew_id`)
    REFERENCES `ssafit`.`crew` (`crew_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`crew_board_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`crew_board_post` (
  `crew_board_post_id` INT NOT NULL AUTO_INCREMENT,
  `crew_id` INT NOT NULL,
  `crew_board_post_title` VARCHAR(100) NOT NULL,
  `crew_board_post_writer` VARCHAR(20) NOT NULL,
  `crew_board_post_content` VARCHAR(500) NOT NULL,
  `crew_board_post_img` VARCHAR(100) NULL,
  `crew_board_post_reg_date` DATETIME NOT NULL,
  PRIMARY KEY (`crew_board_post_id`),
  CONSTRAINT `fk_crew_id`
    FOREIGN KEY (`crew_board_post_id`)
    REFERENCES `ssafit`.`crew_board` (`crew_board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`crew_board_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`crew_board_review` (
  `crew_board_review_id` INT NOT NULL AUTO_INCREMENT,
  `crew_board_post_id` INT NOT NULL,
  `crew_board_review_writer` VARCHAR(20) NOT NULL,
  `crew_board_review_content` VARCHAR(200) NOT NULL,
  `crew_board_review_reg_date` DATETIME NOT NULL,
  PRIMARY KEY (`crew_board_review_id`),
  INDEX `fk_crew_board_post_id_idx` (`crew_board_post_id` ASC) VISIBLE,
  CONSTRAINT `fk_crew_board_post_id`
    FOREIGN KEY (`crew_board_post_id`)
    REFERENCES `mydb`.`crew_board_post` (`crew_board_post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`to_do_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`to_do_list` (
  `to_do_list_id` INT NOT NULL AUTO_INCREMENT,
  `crew_board_id` INT NOT NULL,
  `to_do_list_writer` VARCHAR(20) NOT NULL,
  `to_do_list_content` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`to_do_list_id`),
  INDEX `fk_crew_board_id_idx` (`crew_board_id` ASC) VISIBLE,
  CONSTRAINT `fk_crew_board_id`
    FOREIGN KEY (`crew_board_id`)
    REFERENCES `ssafit`.`crew_board` (`crew_board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ssafit`.`user` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_pass` VARCHAR(30) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `user_phone_number` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(50) NOT NULL,
  `user_athletic_career` INT NOT NULL DEFAULT '0',
  `user_profile_img` VARCHAR(255) NULL DEFAULT NULL,
  `user_is_admin` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_phone_number_UNIQUE` (`user_phone_number` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`video_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`video_board` (
  `video_board_title` VARCHAR(100) NOT NULL,
  `video_board_content` VARCHAR(500) NOT NULL,
  `video_board_id` INT NOT NULL AUTO_INCREMENT,
  `video_board_view_cnt` INT NOT NULL DEFAULT '0',
  `video_board_like_cnt` INT NOT NULL DEFAULT '0',
  `youtube_url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`video_id`),
  UNIQUE INDEX `youtube_url` (`youtube_url` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`crew_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`crew_member` (
  `member_id` VARCHAR(20) NOT NULL,
  `crew_id` INT NOT NULL,
  `crew_board_post_crew_board_post_id` INT NOT NULL,
  INDEX `fk_member_id_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_crew_member_crew1_idx` (`crew_id` ASC) VISIBLE,
  INDEX `fk_crew_member_crew_board_post1_idx` (`crew_board_post_crew_board_post_id` ASC) VISIBLE,
  CONSTRAINT `fk_member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `ssafit`.`user` (`user_id`),
  CONSTRAINT `fk_crew_member_crew1`
    FOREIGN KEY (`crew_id`)
    REFERENCES `ssafit`.`crew` (`crew_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_crew_member_crew_board_post1`
    FOREIGN KEY (`crew_board_post_crew_board_post_id`)
    REFERENCES `mydb`.`crew_board_post` (`crew_board_post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;






-- -----------------------------------------------------
-- Table `ssafit`.`video_board_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`video_board_like` (
  `user_id` VARCHAR(20) NOT NULL,
  `video_board_id` INT NOT NULL,
  INDEX `fk_user_id_video_board_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_video_board_id_video_board_video_id_idx` (`video_board_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_video_board_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`user_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_video_board_id_video_board_video_id`
    FOREIGN KEY (`video_board_id`)
    REFERENCES `ssafit`.`video_board` (`video_id`)
    ON DELETE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ssafit`.`video_board_reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit`.`video_board_reviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `video_board_id` INT NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `review_content` VARCHAR(100) NOT NULL,
  `review_reg_date` TIMESTAMP DEFAULT now(),
  PRIMARY KEY (`review_id`),
  INDEX `fk_video_board_id_idx` (`video_board_id` ASC) VISIBLE,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit`.`user` (`user_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_video_board_id`
    FOREIGN KEY (`video_board_id`)
    REFERENCES `ssafit`.`video_board` (`video_board_id`)
    ON DELETE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM user;
SELECT * FROM video_board;

INSERT user
VALUES ("admin", "1234", "박기현", "01012341234", 3, null, 1);

