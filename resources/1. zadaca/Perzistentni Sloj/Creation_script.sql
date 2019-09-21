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
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`OSOBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`OSOBA` (
  `JMB` INT NOT NULL,
  `Ime` VARCHAR(45) NULL,
  `Prezime` VARCHAR(45) NULL,
  `KorisnickoIme` VARCHAR(45) NULL,
  `Lozinka` VARCHAR(45) NULL,
  PRIMARY KEY (`JMB`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`POPISIVAC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`POPISIVAC` (
  `JMB` INT NOT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_POPISIVAC_OSOBA1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`OSOBA` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ADMINISTRATOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ADMINISTRATOR` (
  `JMB` INT NOT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_ADMINISTRATOR_OSOBA1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`OSOBA` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PKLS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PKLS` (
  `IdPKLS` INT NOT NULL,
  `Grad` VARCHAR(45) NULL,
  `Opstina` VARCHAR(45) NULL,
  PRIMARY KEY (`IdPKLS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLAN_PKLS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLAN_PKLS` (
  `JMB` INT NOT NULL,
  `IdPKLS` INT NOT NULL,
  PRIMARY KEY (`JMB`),
  INDEX `fk_CLAN_PKLS_PKLS1_idx` (`IdPKLS` ASC) VISIBLE,
  CONSTRAINT `fk_CLAN_PKLS_ADMINISTRATOR1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`ADMINISTRATOR` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CLAN_PKLS_PKLS1`
    FOREIGN KEY (`IdPKLS`)
    REFERENCES `mydb`.`PKLS` (`IdPKLS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DE_INSTRUKTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DE_INSTRUKTOR` (
  `JMB` INT NOT NULL,
  `Drzava` VARCHAR(45) NULL,
  `Entitet` VARCHAR(45) NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_DE_INSTRUKTOR_ADMINISTRATOR1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`ADMINISTRATOR` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ADMINISTRATOR_AGENCIJE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ADMINISTRATOR_AGENCIJE` (
  `JMB` INT NOT NULL,
  `NazivAgencije` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_ADMINISTRATOR_AGENCIJE_ADMINISTRATOR1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`ADMINISTRATOR` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`OG_INSTRUKTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`OG_INSTRUKTOR` (
  `JMB` INT NOT NULL,
  `Grad` VARCHAR(45) NULL,
  `Opstina` VARCHAR(45) NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_OG_INSTRUKTOR_ADMINISTRATOR1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`ADMINISTRATOR` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`OCJENA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`OCJENA` (
  `JMB_OG_INSTRUKTOR` INT NOT NULL,
  `JMB_POPISIVAC` INT NOT NULL,
  `Ocjena` INT NULL,
  PRIMARY KEY (`JMB_POPISIVAC`),
  INDEX `fk_OCJENA_OG_INSTRUKTOR1_idx` (`JMB_OG_INSTRUKTOR` ASC) VISIBLE,
  INDEX `fk_OCJENA_POPISIVAC1_idx` (`JMB_POPISIVAC` ASC) VISIBLE,
  CONSTRAINT `fk_OCJENA_OG_INSTRUKTOR1`
    FOREIGN KEY (`JMB_OG_INSTRUKTOR`)
    REFERENCES `mydb`.`OG_INSTRUKTOR` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OCJENA_POPISIVAC1`
    FOREIGN KEY (`JMB_POPISIVAC`)
    REFERENCES `mydb`.`POPISIVAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AKTIVNOST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AKTIVNOST` (
  `Datum` DATE NOT NULL,
  `JMB` INT NOT NULL,
  `BrojPopisnica` INT NULL,
  PRIMARY KEY (`Datum`, `JMB`),
  CONSTRAINT `fk_AKTIVNOST_POPISIVAC1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`POPISIVAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`KONTROLNIK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`KONTROLNIK` (
  `JMB` INT NOT NULL,
  `BrojPopisanihStanova` INT NULL,
  `BrojPopisanihDomacinstava` INT NULL,
  `BrojDomacinstavaKojiSeBavePoljoprivredom` INT NULL,
  `BrojPrisutnihClanovaDomacinstva` INT NULL,
  `BrojOdsutnihClanovaDomacinstva` INT NULL,
  `BrojNeodazvanihLica` INT NULL,
  `BrojNeodazvanihDomacinstava` INT NULL,
  PRIMARY KEY (`JMB`),
  CONSTRAINT `fk_KONTROLNIK_POPISIVAC1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`POPISIVAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`POPISNI_KRUG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`POPISNI_KRUG` (
  `IdPopisnogKruga` INT NOT NULL,
  `Opstina` VARCHAR(100) NULL,
  `Grad` VARCHAR(100) NULL,
  PRIMARY KEY (`IdPopisnogKruga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`POPISIVAC_POPISNI_KRUG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`POPISIVAC_POPISNI_KRUG` (
  `JMB` INT NOT NULL,
  `IdPopisnogKruga` INT NOT NULL,
  `DatumOd` DATE NOT NULL,
  `DatumDo` DATE NOT NULL,
  PRIMARY KEY (`JMB`, `IdPopisnogKruga`),
  INDEX `fk_POPISIVAC_has_POPISNI_KRUG_POPISNI_KRUG1_idx` (`IdPopisnogKruga` ASC) VISIBLE,
  INDEX `fk_POPISIVAC_has_POPISNI_KRUG_POPISIVAC1_idx` (`JMB` ASC) VISIBLE,
  CONSTRAINT `fk_POPISIVAC_has_POPISNI_KRUG_POPISIVAC1`
    FOREIGN KEY (`JMB`)
    REFERENCES `mydb`.`POPISIVAC` (`JMB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_POPISIVAC_has_POPISNI_KRUG_POPISNI_KRUG1`
    FOREIGN KEY (`IdPopisnogKruga`)
    REFERENCES `mydb`.`POPISNI_KRUG` (`IdPopisnogKruga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
