INSERT INTO aktivnost(Datum,IdOsobe,BrojPopisnica) VALUES (NOW(),3,10)
ON DUPLICATE KEY UPDATE
BrojPopisnica = BrojPopisnica + VALUES(BrojPopisnica);

SELECT *
FROM Aktivnost
WHERE IdOsobe = 3;