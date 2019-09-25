INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Nikola","Nikolic","nikola.nikolic","12345");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO de_instruktor(IdOsobe,Drzava,Entitet) values (LAST_INSERT_ID(),"BiH","RS");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Kristijan","Stepanov","kristijan.stepanov","Ke60OQCz4X0zvA8RHr1u0A==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
SET @lastID = LAST_INSERT_ID();
INSERT INTO pkls(Grad,Opstina) values ("Banja Luka", "Banja Luka");
INSERT INTO clan_pkls(IdOsobe,IdPKLS) values (@lastID,LAST_INSERT_ID());

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Marko","Markovic","marko.markovic","123456");
INSERT INTO popisivac(IdOsobe) values (LAST_INSERT_ID());

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Jovan","Jovanovic","jovan.jovanovic","123456");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO og_instruktor(IdOsobe,Grad,Opstina) values (LAST_INSERT_ID(),"Banja Luka","Banja Luka");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Milan","Milanovic","milan.milanovic","123456");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO de_instruktor(IdOsobe,Drzava,Entitet) values (LAST_INSERT_ID(),"BiH","RS");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Petar","Petrovic","petar.petrovic","123456");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
SET @lastID = LAST_INSERT_ID();
INSERT INTO pkls(Grad,Opstina) values ("Banja Luka", "Banja Luka");
INSERT INTO clan_pkls(IdOsobe,IdPKLS) values (@lastID,LAST_INSERT_ID());
/*
UPDATE osoba
SET Ime = ?,
	Prezime = ?,
    KorisnickoIme = ?,
    Lozinka = ?,
    Jezik = ?,
    Pismo = ?
WHERE IdOsobe = ?;
*/