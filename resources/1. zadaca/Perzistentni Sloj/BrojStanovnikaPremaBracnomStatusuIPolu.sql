SELECT COUNT(*) AS Broj
FROM STANOVNIK stanovnik
INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB
INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 21
WHERE popisnicaStanovnika.IdEntiteta like "%" AND popisnicaStanovnika.IdOpstine like "%" AND Pol like "%" AND odgovorStanovnika.Odgovor like CONCAT("%","Nikad oženjen/udata","%");