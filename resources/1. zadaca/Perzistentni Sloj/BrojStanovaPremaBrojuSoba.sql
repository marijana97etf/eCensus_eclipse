SELECT COUNT(*) AS Broj
FROM STAN_DOMACINSTVO stanDomacinstvo
INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva
INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 7
WHERE popisnicaDomacinstva.IdEntiteta = 0 AND popisnicaDomacinstva.IdOpstine = 0 AND odgovorDomacinstva.Odgovor like "%%";