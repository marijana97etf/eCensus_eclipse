-- MySQL Script generated by MySQL Workbench
-- Thu Oct  3 09:46:44 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eCensusNalozi
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eCensusNalozi` ;

-- -----------------------------------------------------
-- Schema eCensusNalozi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eCensusNalozi` DEFAULT CHARACTER SET utf8 ;
USE `eCensusNalozi` ;

-- -----------------------------------------------------
-- Table `eCensusNalozi`.`OSOBA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`OSOBA` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`OSOBA` (
  `IdOsobe` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NULL,
  `Prezime` VARCHAR(45) NULL,
  `KorisnickoIme` VARCHAR(45) NOT NULL,
  `Lozinka` VARCHAR(45) NULL,
  PRIMARY KEY (`IdOsobe`),
  UNIQUE INDEX `KorisnickoIme_UNIQUE` (`KorisnickoIme` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`POPISIVAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`POPISIVAC` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`POPISIVAC` (
  `IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_POPISIVAC_OSOBA1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`OSOBA` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`ADMINISTRATOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`ADMINISTRATOR` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`ADMINISTRATOR` (
  `IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_ADMINISTRATOR_OSOBA1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`OSOBA` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`PKLS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`PKLS` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`PKLS` (
  `IdPKLS` INT NOT NULL AUTO_INCREMENT,
  `Grad` VARCHAR(100) NULL,
  `Opstina` VARCHAR(100) NULL,
  PRIMARY KEY (`IdPKLS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`CLAN_PKLS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`CLAN_PKLS` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`CLAN_PKLS` (
  `IdOsobe` INT NOT NULL,
  `IdPKLS` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  INDEX `fk_CLAN_PKLS_PKLS1_idx` (`IdPKLS` ASC) VISIBLE,
  CONSTRAINT `fk_CLAN_PKLS_ADMINISTRATOR1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`ADMINISTRATOR` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CLAN_PKLS_PKLS1`
    FOREIGN KEY (`IdPKLS`)
    REFERENCES `eCensusNalozi`.`PKLS` (`IdPKLS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`DE_INSTRUKTOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`DE_INSTRUKTOR` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`DE_INSTRUKTOR` (
  `IdOsobe` INT NOT NULL,
  `Drzava` VARCHAR(100) NULL,
  `Entitet` VARCHAR(100) NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_DE_INSTRUKTOR_ADMINISTRATOR1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`ADMINISTRATOR` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`ADMINISTRATOR_AGENCIJE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`ADMINISTRATOR_AGENCIJE` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`ADMINISTRATOR_AGENCIJE` (
  `IdOsobe` INT NOT NULL,
  `NazivAgencije` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_ADMINISTRATOR_AGENCIJE_ADMINISTRATOR1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`ADMINISTRATOR` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`OG_INSTRUKTOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`OG_INSTRUKTOR` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`OG_INSTRUKTOR` (
  `IdOsobe` INT NOT NULL,
  `Grad` VARCHAR(100) NULL,
  `Opstina` VARCHAR(100) NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_OG_INSTRUKTOR_ADMINISTRATOR1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`ADMINISTRATOR` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`OCJENA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`OCJENA` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`OCJENA` (
  `IdOsobe_POPISIVAC` INT NOT NULL,
  `IdOsobe_OG_INSTRUKTOR` INT NOT NULL,
  `Ocjena` INT NULL,
  PRIMARY KEY (`IdOsobe_POPISIVAC`),
  INDEX `fk_OCJENA_OG_INSTRUKTOR1_idx` (`IdOsobe_OG_INSTRUKTOR` ASC) VISIBLE,
  INDEX `fk_OCJENA_POPISIVAC1_idx` (`IdOsobe_POPISIVAC` ASC) VISIBLE,
  CONSTRAINT `fk_OCJENA_OG_INSTRUKTOR1`
    FOREIGN KEY (`IdOsobe_OG_INSTRUKTOR`)
    REFERENCES `eCensusNalozi`.`OG_INSTRUKTOR` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OCJENA_POPISIVAC1`
    FOREIGN KEY (`IdOsobe_POPISIVAC`)
    REFERENCES `eCensusNalozi`.`POPISIVAC` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`AKTIVNOST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`AKTIVNOST` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`AKTIVNOST` (
  `Datum` DATE NOT NULL,
  `IdOsobe` INT NOT NULL,
  `BrojPopisnicaStanovnika` INT NULL,
  `BrojPopisnicaDomacinstva` INT NULL,
  PRIMARY KEY (`Datum`, `IdOsobe`),
  CONSTRAINT `fk_AKTIVNOST_POPISIVAC1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`POPISIVAC` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`OPSTINA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`OPSTINA` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`OPSTINA` (
  `IdOpstine` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(300) NULL,
  PRIMARY KEY (`IdOpstine`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`POPISNI_KRUG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`POPISNI_KRUG` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`POPISNI_KRUG` (
  `IdPopisnogKruga` INT NOT NULL AUTO_INCREMENT,
  `IdOpstine` INT NOT NULL,
  `Grad` VARCHAR(100) NULL,
  `SlikaPopisnogKruga` MEDIUMBLOB NULL,
  PRIMARY KEY (`IdPopisnogKruga`, `IdOpstine`),
  INDEX `fk_POPISNI_KRUG_OPSTINA1_idx` (`IdOpstine` ASC) VISIBLE,
  CONSTRAINT `fk_POPISNI_KRUG_OPSTINA1`
    FOREIGN KEY (`IdOpstine`)
    REFERENCES `eCensusNalozi`.`OPSTINA` (`IdOpstine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`KONTROLNIK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`KONTROLNIK` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`KONTROLNIK` (
  `IdPopisnogKruga` INT NOT NULL,
  `IdOpstine` INT NOT NULL,
  `BrojPopisanihStanova` INT NULL,
  `BrojPopisanihDomacinstava` INT NULL,
  `BrojClanovaDomacinstava` INT NULL,
  PRIMARY KEY (`IdPopisnogKruga`, `IdOpstine`),
  CONSTRAINT `fk_KONTROLNIK_POPISNI_KRUG1`
    FOREIGN KEY (`IdPopisnogKruga` , `IdOpstine`)
    REFERENCES `eCensusNalozi`.`POPISNI_KRUG` (`IdPopisnogKruga` , `IdOpstine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`POPISIVAC_POPISNI_KRUG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`POPISIVAC_POPISNI_KRUG` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`POPISIVAC_POPISNI_KRUG` (
  `IdOsobe` INT NOT NULL,
  `IdPopisnogKruga` INT NOT NULL,
  `IdOpstine` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`, `IdPopisnogKruga`, `IdOpstine`),
  INDEX `fk_POPISIVAC_has_POPISNI_KRUG_POPISIVAC1_idx` (`IdOsobe` ASC) VISIBLE,
  INDEX `fk_POPISIVAC_POPISNI_KRUG_POPISNI_KRUG1_idx` (`IdPopisnogKruga` ASC, `IdOpstine` ASC) VISIBLE,
  CONSTRAINT `fk_POPISIVAC_has_POPISNI_KRUG_POPISIVAC1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`POPISIVAC` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_POPISIVAC_POPISNI_KRUG_POPISNI_KRUG1`
    FOREIGN KEY (`IdPopisnogKruga` , `IdOpstine`)
    REFERENCES `eCensusNalozi`.`POPISNI_KRUG` (`IdPopisnogKruga` , `IdOpstine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`ULICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`ULICA` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`ULICA` (
  `IdUlice` INT NOT NULL AUTO_INCREMENT,
  `IdPopisnogKruga` INT NOT NULL,
  `IdOpstine` INT NOT NULL,
  `Naziv` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`IdUlice`, `IdPopisnogKruga`, `IdOpstine`),
  INDEX `fk_ULICA_POPISNI_KRUG1_idx` (`IdPopisnogKruga` ASC, `IdOpstine` ASC) VISIBLE,
  CONSTRAINT `fk_ULICA_POPISNI_KRUG1`
    FOREIGN KEY (`IdPopisnogKruga` , `IdOpstine`)
    REFERENCES `eCensusNalozi`.`POPISNI_KRUG` (`IdPopisnogKruga` , `IdOpstine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCensusNalozi`.`POWER_USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCensusNalozi`.`POWER_USER` ;

CREATE TABLE IF NOT EXISTS `eCensusNalozi`.`POWER_USER` (
  `IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  CONSTRAINT `fk_POWER_USER_OSOBA1`
    FOREIGN KEY (`IdOsobe`)
    REFERENCES `eCensusNalozi`.`OSOBA` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
