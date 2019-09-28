INSERT INTO popisni_krug(IdPopisnogKruga,Opstina,Grad,SlikaPopisnogKruga) VALUES (2,"Banja Luka","Banja Luka",true);

INSERT INTO ulica(IdUlice,IdPopisnogKruga,Naziv) VALUES (1,2,"Ulica4");
INSERT INTO ulica(IdUlice,IdPopisnogKruga,Naziv) VALUES (2,2,"Ulica5");
INSERT INTO ulica(IdUlice,IdPopisnogKruga,Naziv) VALUES (3,2,"Ulica6");

SELECT *
FROM popisni_krug PopisniKrug
WHERE PopisniKrug.Grad = "Banja Luka" AND PopisniKrug.Opstina = "Banja Luka";

SELECT *
FROM ulica
WHERE IdPopisnogKruga = 2;