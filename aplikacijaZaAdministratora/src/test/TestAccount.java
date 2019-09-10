package test;

import controller.kontroler_za_cuvanje_naloga.CuvanjeNalogaNaFS;
import controller.kontroler_za_cuvanje_naloga.KontrolerZaCuvanjeNaloga;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.SkladisteNaloga;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TestAccount {

    public static void main(String... args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, ClassNotFoundException {
        KorisnikSistema korisnikSistema = new ClanPKLS(
                "3920923",
                "Kristijan",
                "Stepanov",
                "kristijan.stepanov",
                "LeagueOfLegends",
                JEZIK.SRPSKI,
                PISMO.LATINICA);

        KorisnikSistema korisnikSistema2 = new ClanPKLS(
                "42302",
                "Mitar",
                "Miric",
                "mitar.miric",
                "hello",
                JEZIK.SRPSKI,
                PISMO.LATINICA);

//        var skladisteNaloga = new SkladisteNaloga(korisnikSistema, korisnikSistema2);
        var kontrolerZaCuvanjeNaloga = new KontrolerZaCuvanjeNaloga(new CuvanjeNalogaNaFS());
//        if(kontrolerZaCuvanjeNaloga.sacuvajNaloge(skladisteNaloga))
//            System.out.println("Uspjesno čuvanje");
//        else
//            System.out.println("Neuspješno čuvanje");

        kontrolerZaCuvanjeNaloga.ucitajNaloge();
        SkladisteNaloga skladisteNaloga = kontrolerZaCuvanjeNaloga.getSkladisteNaloga();
        System.out.println(skladisteNaloga.get(1).getKorisnickoIme());
    }
}
