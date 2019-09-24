
import java.io.File;
import java.util.LinkedList;

import javax.ws.rs.core.GenericType;

import eCensus.rest.client.CMISKlijent;
import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.DEInstruktorCMISKlijent;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class TestCMISRest {

	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	public static void main(String[] args) {
		
		KorisnikSistema korisnikSistema = new DEInstruktor(2L, "Nikola", "Nikolic", "nikola.nikolic",
				"12345", DRZAVA.BIH, ENTITET.RS, JEZIK.SRPSKI, PISMO.LATINICA, TRUSTSTORE, "sigurnost", KEYSTORE,
				"sigurnost");
		CMISKlijent klijent = new DEInstruktorCMISKlijent(korisnikSistema.getKeyStore(), korisnikSistema.getKeyLozinka(),
				korisnikSistema.getTrustStore(), korisnikSistema.getTrustLozinka(), korisnikSistema.getKorisnickoIme(),
				korisnikSistema.getLozinkaHash());
		
//		 CMISKlijent klijent = new DEInstruktorCMISKlijent(korisnikSistema);
//		DEInstruktor korisnik = null;
		
		
//		KorisnikSistema korisnikSistema = new ClanPKLS(1L, "3920923", "Kristijan", "Stepanov", "kristijan.stepanov", "LeagueOfLegends1111",
//				JEZIK.SRPSKI, PISMO.LATINICA, CMISKlijent.TRUSTSTORE, "sigurnost", CMISKlijent.KEYSTORE,
//				"sigurnost");
//		CMISKlijent klijent = new ClanPKLSCMISKlijent(korisnikSistema.getKeyStore(), korisnikSistema.getKeyLozinka(),
//		korisnikSistema.getTrustStore(), korisnikSistema.getTrustLozinka(), korisnikSistema.getKorisnickoIme(),
//		korisnikSistema.getLozinkaHash());
//		ClanPKLS korisnik = (ClanPKLS)klijent.login(korisnikSistema.getKorisnickoIme(), korisnikSistema.getLozinkaHash());
		
		
		ClanPKLSCMISKlijent clanPKLSklijent = new ClanPKLSCMISKlijent(korisnikSistema);
		System.out.println(clanPKLSklijent);
		ClanPKLS clanPKLS = clanPKLSklijent.getClanPKLS("kristijan.stepanov").readEntity(ClanPKLS.class);
		
		System.out.println(clanPKLS);
		
//		System.out.println("Trazena lista:");
//		for(ClanPKLS clan : clanPKLSklijent.getListuClanovaPKLS().readEntity(new GenericType<LinkedList<ClanPKLS>>() {})) {
//			System.out.println(clan);
//			System.out.println(clan.getClass().getName());
//		}
//		
//		clanPKLSklijent.registrujKorisnika( new ClanPKLS(10L, "3920923", "Kristijan", "Stepanov", "milos.milosevic", "lozinka",
//				JEZIK.SRPSKI, PISMO.LATINICA, "Banja Luka", "Banja Luka", TRUSTSTORE, "sigurnost", KEYSTORE,
//				"sigurnost"));
//		System.out.println(clanPKLSklijent.getClanPKLS("milos.milosevic"));
//		
//		ClanPKLS clan = clanPKLSklijent.getClanPKLS("milos.milosevic").readEntity(ClanPKLS.class);
//		clan.setIme("Marko");
//		clan.setKorisnickoIme("marko.milosevic");
//		clanPKLSklijent.azurirajKorisnika(clan);
//		System.out.println(clanPKLSklijent.getClanPKLS("marko.milosevic"));
//		
//		for(ClanPKLS clan1 : clanPKLSklijent.getListuClanovaPKLS().readEntity(new GenericType<LinkedList<ClanPKLS>>() {})) {
//			System.out.println(clan1);
//			System.out.println(clan1.getClass().getName());
//		}
//		
//		clanPKLSklijent.obrisiKorisnika(clan);
//		System.out.println(clanPKLSklijent.getClanPKLS("marko.milosevic"));

	}

}
