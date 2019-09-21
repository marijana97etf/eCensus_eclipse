SELECT COUNT(*) AS Broj
FROM STAN_DOMACINSTVO stanDomacinstvo
INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva
INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 30
INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva1 on odgovorDomacinstva1.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva1.IdPitanja = 32
WHERE popisnicaDomacinstva.IdEntiteta like "" AND popisnicaDomacinstva.IdOpstine = 0 AND odgovorDomacinstva.Odgovor like "%%" AND odgovorDomacinstva1.Odgovor like "%%";
