package eCensus.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import eCensus.rest.client.CMISKlijent;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA_ENTITET;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class NaloziDAO {

	protected static HashMap<Long, KorisnikSistema> korisnici;

	static {

		korisnici = new HashMap<>();
		korisnici.put(1L,
				new ClanPKLS(1L, "3920923", "Kristijan", "Stepanov", "kristijan.stepanov", "LeagueOfLegends1111",
						JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
						"sigurnost"));
		korisnici.put(2L,new DEInstruktor(2L, "1232132", "Nikola", "Nikolic", "nikola.nikolic", "12345", DRZAVA_ENTITET.RS,
						JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
						"sigurnost"));
		korisnici.put(3L, new Popisivac(3L, "3213211", "Janko", "Jankovic", "janko.Jankovic", "12345", JEZIK.SRPSKI,
				PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE, "sigurnost"));
		korisnici.put(4L, new AdministratorAgencije(4L, "32132211", "admin", "adminovic", "admin", "admin", JEZIK.SRPSKI,
				PISMO.CIRILICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE, "sigurnost"));

	}

	public Collection<KorisnikSistema> getListuKorisnika() {
		return korisnici.values();
	}

	public KorisnikSistema obrisiKorisnika(long id) {
		return korisnici.remove(id);
	}

	public void dodajKorisnika(KorisnikSistema korisnik) {
		korisnici.put(korisnik.getId(), korisnik);
	}

	public void azurirajKorisnika(KorisnikSistema korisnik) {
		korisnici.put(korisnik.getId(), korisnik);
	}
	
	public boolean sadrziKorisnika(KorisnikSistema korisnik) {
		if(korisnici.containsKey(korisnik.getId()))
			return true;
		else 
			return false;
	}

}
