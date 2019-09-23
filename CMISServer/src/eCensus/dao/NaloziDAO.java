package eCensus.dao;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import eCensus.rest.client.CMISKlijent;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class NaloziDAO {
	
	
	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	protected static HashMap<Long, KorisnikSistema> korisnici;

	static {

		korisnici = new HashMap<>();
		korisnici.put(1L,
				new ClanPKLS(1L, "3920923", "Kristijan", "Stepanov", "kristijan.stepanov", "admin",
						JEZIK.SRPSKI, PISMO.LATINICA, "Banja Luka", "Banja Luka", TRUSTSTORE, "sigurnost", KEYSTORE,
						"sigurnost"));
		korisnici.put(2L,new DEInstruktor(2L, "1232132", "Nikola", "Nikolic", "nikola.nikolic", "12345", ENTITET.RS,
						JEZIK.SRPSKI, PISMO.LATINICA, TRUSTSTORE, "sigurnost", KEYSTORE,
						"sigurnost"));
		korisnici.put(3L, new Popisivac(3L, "3213211", "Janko", "Jankovic", "janko.Jankovic", "12345", JEZIK.SRPSKI,
				PISMO.LATINICA, TRUSTSTORE, "sigurnost", KEYSTORE, "sigurnost"));
		korisnici.put(4L, new AdministratorAgencije(4L, "32132211", "admin", "adminovic", "admin", "admin", JEZIK.SRPSKI,
				PISMO.CIRILICA, "AgencijaBIH", TRUSTSTORE, "sigurnost", KEYSTORE, "sigurnost"));

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
	
	public KorisnikSistema getKorisnikSistema(String korisnickoIme) {
		KorisnikSistema korisnikRezultat = null;
		
		for (KorisnikSistema korisnikSistema : getListuKorisnika()) {
			if (korisnickoIme.equals(korisnikSistema.getKorisnickoIme())) {
				korisnikRezultat = korisnikSistema;
			}
		}
		
		return korisnikRezultat;
	}

}
