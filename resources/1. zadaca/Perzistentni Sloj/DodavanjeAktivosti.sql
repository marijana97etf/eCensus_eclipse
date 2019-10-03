INSERT INTO aktivnost(Datum,IdOsobe,BrojPopisnicaStanovnika,BrojPopisnicaDomacinstva) VALUES (NOW(),1,10,10)
ON DUPLICATE KEY UPDATE
BrojPopisnicaStanovnika = BrojPopisnicaStanovnika + VALUES(BrojPopisnicaStanovnika),
BrojPopisnicaDomacinstva = BrojPopisnicaDomacinstva + VALUES(BrojPopisnicaDomacinstva);

SELECT *
FROM Aktivnost
WHERE IdOsobe = 1;