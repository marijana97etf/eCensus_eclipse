INSERT INTO popisivac_popisni_krug(IdOsobe,IdPopisnogKruga,IdOpstine) VALUES (5,2,84);

SELECT Popisivac.IdOsobe,PopisivacPopisniKrug.IdPopisnogKruga,PopisniKrug.IdOpstine,PopisniKrug.Grad,Ulica.Naziv,PopisniKrug.SlikaPopisnogKruga
FROM popisivac Popisivac
INNER JOIN popisivac_popisni_krug PopisivacPopisniKrug on popisivac.IdOsobe = PopisivacPopisniKrug.IdOsobe
INNER JOIN popisni_krug PopisniKrug on PopisniKrug.IdPopisnogKruga = PopisivacPopisniKrug.IdPopisnogKruga
INNER JOIN ulica Ulica on Ulica.IdPopisnogKruga = PopisniKrug.IdPopisnogKruga
WHERE Popisivac.IdOsobe = 5;