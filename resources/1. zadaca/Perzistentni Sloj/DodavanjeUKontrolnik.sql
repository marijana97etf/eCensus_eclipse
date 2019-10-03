INSERT INTO kontrolnik(IdPopisnogKruga,IdOpstine,BrojPopisanihStanova,
						BrojPopisanihDomacinstava,BrojClanovaDomacinstava) VALUES (2,84,1,1,1)
ON DUPLICATE KEY UPDATE
BrojPopisanihStanova = BrojPopisanihStanova + VALUES(BrojPopisanihStanova),
BrojPopisanihDomacinstava = BrojPopisanihDomacinstava + VALUES(BrojPopisanihDomacinstava),
BrojClanovaDomacinstava = BrojClanovaDomacinstava + VALUES(BrojClanovaDomacinstava);

SELECT *
FROM kontrolnik
WHERE IdPopisnogKruga = 2 AND IdOpstine = 84;