
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eCensus.rest.client.CMISKlijent;
import eCensus.rest.client.DEInstruktorCMISKlijent;
import eCensus.rest.client.PopisivacCMISKlijent;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class TestCMISRest {

	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	public static void main(String[] args) {
		
//		KorisnikSistema korisnikSistema = new DEInstruktor(2L, "1232132", "Nikola", "Nikolic", "nikola.nikolic",
//				"12345",ENTITET.RS, JEZIK.SRPSKI, PISMO.LATINICA, TRUSTSTORE, "sigurnost", KEYSTORE,
//				"sigurnost");
		Popisivac korisnikSistema = new Popisivac(3L, "3213211", "Janko", "Jankovic", "janko.Jankovic", "12345", JEZIK.SRPSKI,
				PISMO.LATINICA, TRUSTSTORE, "sigurnost", KEYSTORE, "sigurnost");
		
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
		
		
//		ClanPKLSCMISKlijent clanPKLSklijent = new ClanPKLSCMISKlijent(korisnikSistema);
//		System.out.println(clanPKLSklijent);
//		ClanPKLS clanPKLS = clanPKLSklijent.getClanPKLS("kristijan.stepanov").readEntity(ClanPKLS.class);
//
//		System.out.println(clanPKLS);
		
		PopisivacCMISKlijent cmisKlijent = new PopisivacCMISKlijent(korisnikSistema);
//		Popisivac popisivac  = cmisKlijent.getPopisivac(korisnikSistema.getKorisnickoIme()).readEntity(Popisivac.class);
		
		PopisniKrug krug  = new PopisniKrug("Prijedor","Prijedor");
		byte[] slikaBytes = null;
		try {
			slikaBytes = Files.readAllBytes(Paths.get("resources/BanjaLukaMapa.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		krug.setSlikaBytes(slikaBytes);
		LinkedList<PopisniKrug> lista = new LinkedList<PopisniKrug>();
		lista.add(krug);
		cmisKlijent.dodajPopisneKrugovePopisivacu(korisnikSistema,lista );
		
		cmisKlijent.azurirajPopisneKrugovePopisivaca(korisnikSistema, lista);
		
		Response odgovor = cmisKlijent.getPopisneKrugovePopisivaca(korisnikSistema);
		System.out.println(odgovor.getStatusInfo().getFamily());
		System.out.println(odgovor.getStatus() + " " + odgovor.getStatusInfo());
		if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily()))
		{
			LinkedList<PopisniKrug> krugovi = odgovor.readEntity(new GenericType<LinkedList<PopisniKrug>>() {});
			for(PopisniKrug popisniKrug : krugovi) {
				System.out.println(popisniKrug.getGrad());
				System.out.println(popisniKrug.getGrad());
				System.out.println(popisniKrug.getSlikaBytes().length);
				try(FileOutputStream out = new FileOutputStream("resources\\outSlika1.jpg")){
					out.write(popisniKrug.getSlikaBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	
//		
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
