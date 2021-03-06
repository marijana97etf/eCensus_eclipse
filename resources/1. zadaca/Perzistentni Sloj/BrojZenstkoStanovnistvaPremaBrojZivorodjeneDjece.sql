SELECT COUNT(*) AS Broj
FROM STANOVNIK stanovnik
INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB
INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 23
WHERE popisnicaStanovnika.IdEntiteta = 0 AND popisnicaStanovnika.IdOpstine = 0 AND Pol = "Zenski" AND CONVERT(odgovorStanovnika.Odgovor, UNSIGNED INTEGER) = 0;