INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Marko","Markovic","mm","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO popisivac(IdOsobe) values (LAST_INSERT_ID());

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Nikola","Nikolic","nn","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO de_instruktor(IdOsobe,Drzava,Entitet) values (LAST_INSERT_ID(),"BiH","RS");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Đorđe","Đorđević","đđ","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO administrator_agencije(IdOsobe,NazivAgencije) values (LAST_INSERT_ID(),"AgencijaBiH");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Tiha","Spic","ts","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO administrator_agencije(IdOsobe,NazivAgencije) values (LAST_INSERT_ID(),"AgencijaBiH");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Kristijan","Stepanov","ks","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
SET @lastID = LAST_INSERT_ID();
INSERT INTO pkls(Grad,Opstina) values ("Banja Luka", "Banja Luka");
INSERT INTO clan_pkls(IdOsobe,IdPKLS) values (@lastID,LAST_INSERT_ID());

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Jovan","Jovanovic","jj","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO og_instruktor(IdOsobe,Grad,Opstina) values (LAST_INSERT_ID(),"Banja Luka","Banja Luka");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Milan","Milanovic","mimi","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
INSERT INTO de_instruktor(IdOsobe,Drzava,Entitet) values (LAST_INSERT_ID(),"BiH","RS");

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values ("Petar","Petrovic","pp","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID());
SET @lastID = LAST_INSERT_ID();
INSERT INTO pkls(Grad,Opstina) values ("Banja Luka", "Banja Luka");
INSERT INTO clan_pkls(IdOsobe,IdPKLS) values (@lastID,LAST_INSERT_ID());

INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) VALUES ("Admin","Admin","admin","DMF1ucDxtqgxw5niaXcmYQ==");
INSERT INTO power_user(IdOsobe) VALUES (LAST_INSERT_ID());
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