SELECT *
FROM popisni_krug PopisniKrug
INNER JOIN ulica Ulica on Ulica.IdPopisnogKruga = PopisniKrug.IdPopisnogKruga
/*WHERE Ulica.Naziv = "" AND PopisniKrug.Grad = "" AND PopisniKrug.IdOpstine = ""*/;