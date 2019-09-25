SELECT pitanje.Sadrzaj, odgovor.Odgovor FROM popisnica_domacinstvo_odgovor odgovor
INNER JOIN popisnica_domacinstva_pitanje pitanje on pitanje.IdPitanja = odgovor.IdPitanja;

SELECT * FROM clan_domacinstva clanovi;