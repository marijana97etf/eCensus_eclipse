SELECT COUNT(DISTINCT(popisnicaDomacinstva.IdZgrade)) AS Broj
FROM STAN_DOMACINSTVO stanDomacinstvo
INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva
WHERE popisnicaDomacinstva.IdEntiteta = 0 AND popisnicaDomacinstva.IdOpstine = 0 AND (
    SELECT COUNT(*) AS Broj
	FROM STAN_DOMACINSTVO stanDomacinstvo1
	INNER JOIN popisnica_domacinstva popisnicaDomacinstva1 on stanDomacinstvo1.IdStanaDomacinstva = popisnicaDomacinstva1.IdStanaDomacinstva
	WHERE popisnicaDomacinstva1.IdEntiteta = popisnicaDomacinstva.IdEntiteta
      AND popisnicaDomacinstva1.IdOpstine = popisnicaDomacinstva.IdOpstine
      AND popisnicaDomacinstva1.IdPopisnogKruga = popisnicaDomacinstva.IdPopisnogKruga
      AND popisnicaDomacinstva1.IdZgrade = popisnicaDomacinstva.IdZgrade
    ) = 3;