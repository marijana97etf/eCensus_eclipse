INSERT INTO ocjena(IdOsobe_POPISIVAC,IdOsobe_OG_INSTRUKTOR,Ocjena) VALUES (5,6,5)
ON DUPLICATE KEY UPDATE
Ocjena = VALUES(Ocjena);

SELECT *
FROM ocjena
WHERE IdOsobe_POPISIVAC = 5;