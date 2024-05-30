-- MySQL Workbench Synchronization
-- Generated: 2024-02-25 21:08
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: laris

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `mydb`.`Membre` (
  `idMembre` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(25) NOT NULL,
  `prenom` VARCHAR(25) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `region` VARCHAR(25) NULL DEFAULT NULL,
  `langue` VARCHAR(25) NULL DEFAULT NULL,
<<<<<<< HEAD
  `photoProfilPath` VARCHAR(255) NULL DEFAULT NULL,
=======
  `photoProfil` VARCHAR(25) NULL DEFAULT NULL,
>>>>>>> ca74659b1a1103595166f83dd29633474ac0ec57
  `bio` LONGTEXT NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `privilege` TINYINT(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idMembre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Challenges` (
  `idChallenge` INT(11) NOT NULL AUTO_INCREMENT,
  `idMembre` INT(11) NOT NULL,
  `challenge_name` VARCHAR(100) NOT NULL,
  `firstSetComplete` TINYINT(1) NOT NULL DEFAULT 0,
  `secondSetComplete` TINYINT(1) NOT NULL DEFAULT 0,
  `thirdSetComplete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idChallenge`),
  FOREIGN KEY (`idMembre`) REFERENCES `Membre`(`idMembre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`ChallengesElement` (
    `challenge_id` INT(11)NOT NULL AUTO_INCREMENT,
    `challenge_name` VARCHAR(100) NOT NULL,
    `challenge_description` VARCHAR(100) NOT NULL,
    `challenge_image_url` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`challenge_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO mydb.ChallengesElement (challenge_name, challenge_description, challenge_image_url)
VALUES ('Italian', 'Introduction to Italian', 'https://img.icons8.com/dusk/64/italy.png');

INSERT INTO mydb.ChallengesElement (challenge_name, challenge_description, challenge_image_url)
VALUES ('French', 'Introduction to French', 'https://img.icons8.com/dusk/64/france.png');

INSERT INTO mydb.ChallengesElement (challenge_name, challenge_description, challenge_image_url)
VALUES ('Spanish', 'Introduction to Spanish', 'https://img.icons8.com/dusk/64/spain.png');

INSERT INTO mydb.ChallengesElement (challenge_name, challenge_description, challenge_image_url)
VALUES ('German', 'Introduction to German', 'https://img.icons8.com/dusk/64/germany.png');

CREATE TABLE IF NOT EXISTS `mydb`.`Forum` (
  `idForum` INT(11) NOT NULL,
  `nomForum` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `photo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idForum`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Publication` (
  `idPublication` INT(11) NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  `like` INT(11) NULL DEFAULT NULL,
  `idMembre` INT(11) NOT NULL,
  PRIMARY KEY (`idPublication`),
  UNIQUE INDEX `idPublication_UNIQUE` (`idPublication` ASC) VISIBLE,
  INDEX `idMembre_idx` (`idMembre` ASC) VISIBLE,
  CONSTRAINT `idMembre`
    FOREIGN KEY (`idMembre`)
    REFERENCES `mydb`.`Membre` (`idMembre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Region` (
  `idRegion` INT(11) NOT NULL,
  `region` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idRegion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Langue` (
  `idLangue` INT(11) NOT NULL,
  `langue` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idLangue`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Administrateur` (
  `idAdmin` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(25) NOT NULL,
  `prenom` VARCHAR(25) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`Commentaire` (
  `idcommentaire` INT(11) NOT NULL,
  `commentaire` MEDIUMTEXT NOT NULL,
  `idPublication` INT(11) NOT NULL,
  PRIMARY KEY (`idcommentaire`),
  INDEX `idPublication_idx` (`idPublication` ASC) VISIBLE,
  CONSTRAINT `idPublication`
    FOREIGN KEY (`idPublication`)
    REFERENCES `mydb`.`Publication` (`idPublication`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
