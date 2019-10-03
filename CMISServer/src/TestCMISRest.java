
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import eCensus.rest.client.CMISKlijent;
import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.DEInstruktorCMISKlijent;
import eCensus.rest.client.OGInstruktorCMISKLijent;
import eCensus.rest.client.PopisivacCMISKlijent;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import model.pracenje_popisa.izvjestaji_o_popisivacu.Kontrolnik;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class TestCMISRest {

	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	public static void main(String[] args) {
		
		KorisnikSistema korisnikSistema = new DEInstruktor(2L, "Nikola", "Nikolic", "nikola.nikolic",
				"Ke60OQCz4X0zvA8RHr1u0A==", DRZAVA.BIH, ENTITET.RS, TRUSTSTORE, "sigurnost", KEYSTORE,
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

		
		List<String> ulice = new ArrayList<String>();
		ulice.add("Ulica1");
		ulice.add("Ulica2");
		ulice.add("Ulica3");
		PopisniKrug popisniKrug1 = new PopisniKrug(84,"Banja Luka",ulice,null);
		
		/*
		try {
			RandomAccessFile raf = new RandomAccessFile(new File("D:\\Desktop\\1920x1080 WHITE.jpg"),"rw");
			byte[] bytes = new byte[(int) raf.length()];
			raf.readFully(bytes);
			System.out.println(bytes.length);
			popisniKrug1.setSlikaBytes(bytes);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/

		clanPKLSklijent.dodajPopisniKrug(popisniKrug1);
		
		System.out.println();
		System.out.println("Popisni Krugovi: ");
		List<PopisniKrug> lista = Arrays.asList(clanPKLSklijent.getListaPopisnihKrugova("Banja Luka", 84).readEntity(PopisniKrug[].class));
		for(PopisniKrug popisniKrug : lista) {
			System.out.println(popisniKrug.getId());
			System.out.println(popisniKrug.getIdOpstine());
			System.out.println(popisniKrug.getGrad());
			popisniKrug.getUlice().forEach(e -> System.out.print(e + ","));
			System.out.println(popisniKrug.getSlikaBytes().length);
		}
		
		PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(korisnikSistema);
		
		System.out.println();
		System.out.println("Popisivaci: ");
		List<Popisivac> listaPopisivaca = Arrays.asList(popisivacCMISKlijent.getListaPopisivaca().readEntity(Popisivac[].class));
		for(Popisivac popisivac : listaPopisivaca) {
			System.out.println(popisivac.getId());
			System.out.println(popisivac.getIme());
			System.out.println(popisivac.getPrezime());
			System.out.println(popisivac.getKorisnickoIme());
		}
		
		System.out.println();
		System.out.println("Unesite podatke: ");
		Scanner scanner = new Scanner(System.in);
		System.out.print("IdPopisivaca: ");
		int idPopisivaca = scanner.nextInt();
		System.out.print("IdPopisnogKruga: ");
		int idPopisnogKruga = scanner.nextInt();
		System.out.print("IdOpstine: ");
		int idOpstine = scanner.nextInt();
		
		popisivacCMISKlijent.dodajPopisneKrugovePopisivacu(idPopisivaca, idPopisnogKruga, idOpstine);
		
		System.out.println();
		System.out.println("Lista popisnih krugova popisivaca: ");
		List<PopisniKrug> listaPopisnihKrugova = Arrays.asList(popisivacCMISKlijent.getListaPopisnihKrugova(5).readEntity(PopisniKrug[].class));
		for(PopisniKrug popisniKrug : listaPopisnihKrugova) {
			System.out.println(popisniKrug.getId());
			System.out.println(popisniKrug.getIdOpstine());
			System.out.println(popisniKrug.getGrad());
			popisniKrug.getUlice().forEach(e -> System.out.print(e + ","));
			System.out.println(popisniKrug.getSlikaBytes().length);
		}
		
		popisivacCMISKlijent.azurirajKontrolnik(new Kontrolnik(idPopisnogKruga,idOpstine,10,10,10));
		
		popisivacCMISKlijent.obrisiPopisniKrugPopisivaca(idPopisivaca, idPopisnogKruga, idOpstine);
		
		clanPKLSklijent.obrisiPopisniKrug(idPopisnogKruga, idOpstine);
		
		System.out.println();
		System.out.println("Lista popisnih krugova nakon brisanja posljednjeg popisnog kruga: ");
		List<PopisniKrug> lista1 = Arrays.asList(clanPKLSklijent.getListaPopisnihKrugova("Banja Luka", 84).readEntity(PopisniKrug[].class));
		for(PopisniKrug popisniKrug : lista1) {
			System.out.println(popisniKrug.getId());
			System.out.println(popisniKrug.getIdOpstine());
			System.out.println(popisniKrug.getGrad());
			popisniKrug.getUlice().forEach(e -> System.out.print(e + ","));
			System.out.println(popisniKrug.getSlikaBytes().length);
		}
		
		/*
		Response odgovor = popisivacCMISKlijent.registrujKorisnika(new Popisivac("Ime","Prezime","KorisnickoIme1",KorisnikSistema.napraviHesLozinke("123456789")));
		if(odgovor.getStatus() == Status.CREATED.getStatusCode()) {
			System.out.println("OK");
		} else {
			System.out.println(odgovor.getStatus());
			System.out.println("NOK");
		}

		Response response = popisivacCMISKlijent.getListaPopisivaca();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			List<KorisnikSistema> korisnici = Arrays.asList(response.readEntity(KorisnikSistema[].class));
			for(KorisnikSistema korisnik : korisnici) {
				System.out.println(korisnik);
			}
		}
		
		Response responseAktivnost = popisivacCMISKlijent.azurirajAktivostPopisivaca(5, new DnevnaAktivnost(LocalDate.now(),10,10));
		if(responseAktivnost.getStatus() == Status.OK.getStatusCode()) {
			String ok = responseAktivnost.readEntity(String.class);
			System.out.println(ok);
		}
		
		System.out.println(clanPKLS);
		*/
		
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
