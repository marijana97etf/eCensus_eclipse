SELECT Stanovnik.*, Popisnica.IdOpstine, pitanje.Sadrzaj, odgovor.Odgovor  FROM popisnica_stanovnika_odgovor odgovor
INNER JOIN popisnica_stanovnistva_pitanje pitanje on pitanje.IdPitanja = odgovor.IdPitanja
INNER JOIN popisnica_stanovnika Popisnica ON Popisnica.IdPopisnice = odgovor.IdPopisnice
INNER JOIN stanovnik Stanovnik ON Stanovnik.JMB = Popisnica.JMB;