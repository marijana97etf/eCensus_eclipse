INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) VALUES ("Admin","Admin","admin","ISMvKXpXpadDiUoOSoAfww==")
ON DUPLICATE KEY UPDATE
Lozinka = "ISMvKXpXpadDiUoOSoAfww==";
INSERT INTO power_user(IdOsobe) VALUES (LAST_INSERT_ID());