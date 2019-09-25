select osoba.*, Tip from (
  select "Popisivac" AS Tip, IdOsobe from popisivac Popisivac
  union
  select "OGInstruktor" AS Tip, IdOsobe from og_instruktor OGInstruktor
  union
  select "AdministratorAgencije" AS Tip, IdOsobe from administrator_agencije AdministratorAgencije
  union
  select "DEInstruktor" AS Tip, IdOsobe from de_instruktor DEInstruktor
  union
  select "ClanPKLS" AS Tip, IdOsobe from clan_pkls ClanPKLS
) unija
INNER JOIN osoba on osoba.IdOsobe = unija.IdOsobe;