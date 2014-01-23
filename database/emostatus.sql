SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `emostatusdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `emostatusdb` ;

-- -----------------------------------------------------
-- Table `emostatusdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`users` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`users` (
  `id` INT NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `full_name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`monitored_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`monitored_users` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`monitored_users` (
  `id` INT NOT NULL ,
  `full_name` VARCHAR(45) NOT NULL ,
  `description` MEDIUMTEXT NOT NULL ,
  `monitoring_activated` TINYINT(1) NOT NULL ,
  `monit_skype_activated` TINYINT(1) NOT NULL ,
  `monit_record_activated` TINYINT(1) NOT NULL ,
  `alert_sms_activated` TINYINT(1) NOT NULL ,
  `alert_email_activated` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`monitorings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`monitorings` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`monitorings` (
  `user_id` INT NOT NULL ,
  `monitorized_user_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `monitorized_user_id`) ,
  INDEX `fk_user_id` (`user_id` ASC) ,
  INDEX `fk_monitorized_user_id` (`monitorized_user_id` ASC) ,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `emostatusdb`.`users` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_monitorized_user_id`
    FOREIGN KEY (`monitorized_user_id` )
    REFERENCES `emostatusdb`.`monitored_users` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`monitoring_modes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`monitoring_modes` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`monitoring_modes` (
  `id` INT NOT NULL ,
  `mode` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`first_classification_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`first_classification_groups` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`first_classification_groups` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `group_name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`second_classification_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`second_classification_groups` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`second_classification_groups` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `group_name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`emotions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`emotions` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`emotions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `first_class_group_id` INT NOT NULL ,
  `second_class_group_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_emotions_1` (`first_class_group_id` ASC) ,
  INDEX `fk_emotions_2` (`second_class_group_id` ASC) ,
  CONSTRAINT `fk_emotions_1`
    FOREIGN KEY (`first_class_group_id` )
    REFERENCES `emostatusdb`.`first_classification_groups` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emotions_2`
    FOREIGN KEY (`second_class_group_id` )
    REFERENCES `emostatusdb`.`second_classification_groups` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `emostatusdb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`status` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`status` (
  `id` INT NOT NULL ,
  `monitoring_mode` INT NOT NULL ,
  `date` DATETIME NOT NULL ,
  `emotion_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_monitoring_mode` (`monitoring_mode` ASC) ,
  INDEX `fk_emotion_id` (`emotion_id` ASC) ,
  CONSTRAINT `fk_monitoring_mode`
    FOREIGN KEY (`monitoring_mode` )
    REFERENCES `emostatusdb`.`monitoring_modes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emotion_id`
    FOREIGN KEY (`emotion_id` )
    REFERENCES `emostatusdb`.`emotions` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`status_monitored_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`status_monitored_users` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`status_monitored_users` (
  `status_id` INT NOT NULL ,
  `monitored_user_id` INT NOT NULL ,
  PRIMARY KEY (`status_id`, `monitored_user_id`) ,
  INDEX `fk_status_id` (`status_id` ASC) ,
  INDEX `fk_monitorized_user_id` (`monitored_user_id` ASC) ,
  CONSTRAINT `fk_status_id`
    FOREIGN KEY (`status_id` )
    REFERENCES `emostatusdb`.`status` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_monitorized_user_id0`
    FOREIGN KEY (`monitored_user_id` )
    REFERENCES `emostatusdb`.`monitored_users` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`email_alert_monitored_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`email_alert_monitored_users` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`email_alert_monitored_users` (
  `monitored_user_id` INT NOT NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`monitored_user_id`) ,
  INDEX `fk_email_alert_monitored_users` (`monitored_user_id` ASC) ,
  CONSTRAINT `fk_email_alert_monitored_users`
    FOREIGN KEY (`monitored_user_id` )
    REFERENCES `emostatusdb`.`monitored_users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`sms_alert_monitored_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`sms_alert_monitored_users` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`sms_alert_monitored_users` (
  `monitored_user_id` INT NOT NULL ,
  `phone_number` VARCHAR(45) NULL ,
  PRIMARY KEY (`monitored_user_id`) ,
  INDEX `fk_sms_alert_monitored_users` (`monitored_user_id` ASC) ,
  CONSTRAINT `fk_sms_alert_monitored_users`
    FOREIGN KEY (`monitored_user_id` )
    REFERENCES `emostatusdb`.`monitored_users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`recordings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`recordings` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`recordings` (
  `id` INT NOT NULL ,
  `path` VARCHAR(45) NOT NULL ,
  `date` DATETIME NOT NULL ,
  `duration` INT NULL ,
  `monitoring_mode` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_recordings_1` (`monitoring_mode` ASC) ,
  CONSTRAINT `fk_recordings_1`
    FOREIGN KEY (`monitoring_mode` )
    REFERENCES `emostatusdb`.`monitoring_modes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emostatusdb`.`chunks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emostatusdb`.`chunks` ;

CREATE  TABLE IF NOT EXISTS `emostatusdb`.`chunks` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `recording_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_chunks_1` (`recording_id` ASC) ,
  CONSTRAINT `fk_chunks_1`
    FOREIGN KEY (`recording_id` )
    REFERENCES `emostatusdb`.`recordings` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
