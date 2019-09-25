INSERT INTO popisni_krug(IdPopisnogKruga,Opstina,Grad) VALUES (0,"Banja Luka","Banja Luka");

INSERT INTO popisivac_popisni_krug(IdOsobe,IdPopisnogKruga,DatumOd,DatumDo,SlikaPopisnogKruga) VALUES (3,0,NOW(),NOW(),true);

INSERT INTO ulica(IdOsobe,IdPopisnogKruga,Naziv) VALUES (3,0,"Ulica");


SELECT Popisivac.IdOsobe,PopisivacPopisniKrug.IdPopisnogKruga,PopisniKrug.Opstina,PopisniKrug.Grad,Ulica.Naziv,PopisivacPopisniKrug.DatumOd,PopisivacPopisniKrug.DatumDo,PopisivacPopisniKrug.SlikaPopisnogKruga
FROM popisivac Popisivac
INNER JOIN popisni_krug PopisniKrug
INNER JOIN popisivac_popisni_krug PopisivacPopisniKrug on popisivac.IdOsobe = PopisivacPopisniKrug.IdOsobe AND PopisniKrug.IdPopisnogKruga = PopisivacPopisniKrug.IdPopisnogKruga
INNER JOIN ulica Ulica on Ulica.IdOsobe = PopisivacPopisniKrug.IdOsobe AND Ulica.IdPopisnogKruga = PopisivacPopisniKrug.IdPopisnogKruga
WHERE Popisivac.IdOsobe = 3;