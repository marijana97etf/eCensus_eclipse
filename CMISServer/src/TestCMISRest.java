
import java.util.LinkedList;

import javax.ws.rs.core.GenericType;

import eCensus.rest.client.CMISKlijent;
import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.DEInstruktorCMISKlijent;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA_ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class TestCMISRest {

	public static String TRUSTSTORE = "C:\\OpenSSL-Win64\\OpenSSL-workspace\\certs\\clientTrustStore.p12";
	public static String KEYSTORE = "C:\\OpenSSL-Win64\\OpenSSL-workspace\\certs\\clientStore.p12";

	public static void main(String[] args) {
		
		KorisnikSistema korisnikSistema = new DEInstruktor(2L, "1232132", "Nikola", "Nikolic", "nikola.nikolic",
				"12345",DRZAVA_ENTITET.RS, JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
				"sigurnost");
		CMISKlijent klijent = new DEInstruktorCMISKlijent(korisnikSistema.getKeyStore(), korisnikSistema.getKeyLozinka(),
				korisnikSistema.getTrustStore(), korisnikSistema.getTrustLozinka(), korisnikSistema.getKorisnickoIme(),
				korisnikSistema.getLozinkaHash());
		
//		 CMISKlijent klijent = new DEInstruktorCMISKlijent(korisnikSistema);
		DEInstruktor korisnik = (DEInstruktor)klijent.login(korisnikSistema.getKorisnickoIme(), korisnikSistema.getLozinkaHash());
		
		
//		KorisnikSistema korisnikSistema = new ClanPKLS(1L, "3920923", "Kristijan", "Stepanov", "kristijan.stepanov", "LeagueOfLegends1111",
//				JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
//				"sigurnost");
//		CMISKlijent klijent = new ClanPKLSCMISKlijent(korisnikSistema.getKeyStore(), korisnikSistema.getKeyLozinka(),
//		korisnikSistema.getTrustStore(), korisnikSistema.getTrustLozinka(), korisnikSistema.getKorisnickoIme(),
//		korisnikSistema.getLozinkaHash());
//		ClanPKLS korisnik = (ClanPKLS)klijent.login(korisnikSistema.getKorisnickoIme(), korisnikSistema.getLozinkaHash());
		
		
		ClanPKLSCMISKlijent clanPKLSklijent = new ClanPKLSCMISKlijent(korisnik);
		ClanPKLS clanPKLS = clanPKLSklijent.getClanPKLS("kristijan.stepanov").readEntity(ClanPKLS.class);

		System.out.println(clanPKLS);
		
		System.out.println("Trazena lista:");
		for(ClanPKLS clan : clanPKLSklijent.getListuClanovaPKLS().readEntity(new GenericType<LinkedList<ClanPKLS>>() {})) {
			System.out.println(clan);
			System.out.println(clan.getClass().getName());
		}
		
		clanPKLSklijent.registrujKorisnika( new ClanPKLS(10L, "3920923", "Kristijan", "Stepanov", "milos.milosevic", "lozinka",
				JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
				"sigurnost"));
		System.out.println(clanPKLSklijent.getClanPKLS("milos.milosevic"));
		
		ClanPKLS clan = clanPKLSklijent.getClanPKLS("milos.milosevic").readEntity(ClanPKLS.class);
		clan.setIme("Marko");
		clan.setKorisnickoIme("marko.milosevic");
		clanPKLSklijent.azurirajKorisnika(clan);
		System.out.println(clanPKLSklijent.getClanPKLS("marko.milosevic"));
		
		for(ClanPKLS clan1 : clanPKLSklijent.getListuClanovaPKLS().readEntity(new GenericType<LinkedList<ClanPKLS>>() {})) {
			System.out.println(clan1);
			System.out.println(clan1.getClass().getName());
		}
		
		clanPKLSklijent.obrisiKorisnika(clan);
		System.out.println(clanPKLSklijent.getClanPKLS("marko.milosevic"));

	}

}
