INSERT INTO kontrolnik(IdPopisnogKruga,IdOpstine,BrojPopisanihStanova,
						BrojPopisanihDomacinstava,BrojDomacinstavaKojiSeBavePoljoprivredom,
                        BrojPrisutnihClanovaDomacinstva,BrojOdsutnihClanovaDomacinstva,
                        BrojNeodazvanihLica,BrojNeodazvanihDomacinstava) VALUES (1,84,1,1,1,1,1,1,1)
ON DUPLICATE KEY UPDATE
BrojPopisanihStanova = BrojPopisanihStanova + VALUES(BrojPopisanihStanova),
BrojPopisanihDomacinstava = BrojPopisanihDomacinstava + VALUES(BrojPopisanihDomacinstava),
BrojDomacinstavaKojiSeBavePoljoprivredom = BrojDomacinstavaKojiSeBavePoljoprivredom + VALUES(BrojDomacinstavaKojiSeBavePoljoprivredom),
BrojPrisutnihClanovaDomacinstva = BrojPrisutnihClanovaDomacinstva + VALUES(BrojPrisutnihClanovaDomacinstva),
BrojOdsutnihClanovaDomacinstva = BrojOdsutnihClanovaDomacinstva + VALUES(BrojOdsutnihClanovaDomacinstva),
BrojNeodazvanihLica = BrojNeodazvanihLica + VALUES(BrojNeodazvanihLica),
BrojNeodazvanihDomacinstava = BrojNeodazvanihDomacinstava + VALUES(BrojNeodazvanihDomacinstava);

SELECT *
FROM kontrolnik
WHERE IdPopisnogKruga = 1 AND IdOpstine = 84;