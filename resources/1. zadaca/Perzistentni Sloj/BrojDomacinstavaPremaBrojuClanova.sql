SELECT COUNT(*) AS Broj
FROM STAN_DOMACINSTVO stanDomacinstvo
INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva
WHERE popisnicaDomacinstva.IdEntiteta = 0 AND popisnicaDomacinstva.IdOpstine = 0 AND (
	SELECT COUNT(*)
    FROM clan_domacinstva clanDomacinstva
	WHERE clanDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice
    ) = 2;
