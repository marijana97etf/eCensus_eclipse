INSERT INTO popisni_krug(IdPopisnogKruga,IdOpstine,Grad,SlikaPopisnogKruga) VALUES (2,84,"Banja Luka",true);

INSERT INTO ulica(IdUlice,IdPopisnogKruga,IdOpstine,Naziv) VALUES (1,2,84,"Ulica4");
INSERT INTO ulica(IdUlice,IdPopisnogKruga,IdOpstine,Naziv) VALUES (2,2,84,"Ulica5");
INSERT INTO ulica(IdUlice,IdPopisnogKruga,IdOpstine,Naziv) VALUES (3,2,84,"Ulica6");

SELECT *
FROM popisni_krug PopisniKrug
WHERE PopisniKrug.Grad = "Banja Luka" AND PopisniKrug.IdOpstine = 84;

SELECT *
FROM ulica
WHERE IdPopisnogKruga = 2;