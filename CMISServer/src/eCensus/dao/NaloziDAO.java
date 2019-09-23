package eCensus.dao;

import java.util.Collection;
import model.korisnicki_nalozi.KorisnikSistema;

public interface NaloziDAO {

	public Collection<KorisnikSistema> getListuKorisnika();

	public boolean obrisiKorisnika(long id);

	public boolean dodajKorisnika(KorisnikSistema korisnik);

	public boolean azurirajKorisnika(KorisnikSistema korisnik);
	
	public boolean sadrziKorisnika(KorisnikSistema korisnik);
	
	public KorisnikSistema getKorisnikSistema(String korisnickoIme);

}
