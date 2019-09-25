SELECT pitanje.Sadrzaj, odgovor.Odgovor FROM popisnica_stanovnika_odgovor odgovor
INNER JOIN popisnica_stanovnistva_pitanje pitanje on pitanje.IdPitanja = odgovor.IdPitanja;