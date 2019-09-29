package model.table_input_models;

import controller.kontroler_formi.KontrolerFormeZaPrijavu;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;

public class PopisivacInputModel extends KorisnikInputModel {
    public PopisivacInputModel(KorisnikSistema korisnikSistema) {
        super(korisnikSistema);
        if(KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof ClanPKLS) {
            var brisanjeButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Obriši")));
            var izmjenaButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Izmjeni")));
            Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
        }
        else
        {
            var pregledajAktivnosti = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Pregled aktivnosti")));
            Buttoni = new SimpleObjectProperty[] { pregledajAktivnosti };
        }

    }
}
