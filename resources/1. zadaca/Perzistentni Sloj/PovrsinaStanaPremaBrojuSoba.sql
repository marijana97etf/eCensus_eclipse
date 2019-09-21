SELECT SUM(CONVERT(SUBSTRING(odgovorDomacinstva.Odgovor,1,length(odgovorDomacinstva.Odgovor) - 1), UNSIGNED INTEGER)) AS UkupnaPovrsina
FROM STAN_DOMACINSTVO stanDomacinstvo
INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva
INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 6
INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva1 on odgovorDomacinstva1.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva1.IdPitanja = 7
WHERE popisnicaDomacinstva.IdEntiteta = 0 AND popisnicaDomacinstva.IdOpstine = 0 AND odgovorDomacinstva1.Odgovor like "%%";