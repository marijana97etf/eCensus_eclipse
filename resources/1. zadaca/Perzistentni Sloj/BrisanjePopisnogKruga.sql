DELETE
FROM ulica
WHERE IdPopisnogKruga = 2 AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = 2) = 0;

DELETE
FROM popisni_krug
WHERE IdPopisnogKruga = 2 AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = 2) = 0;

SELECT *
FROM popisni_krug PopisniKrug
WHERE PopisniKrug.Grad = "Banja Luka" AND PopisniKrug.Opstina = "Banja Luka";

SELECT *
FROM ulica
WHERE IdPopisnogKruga = 2;