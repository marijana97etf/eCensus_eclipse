package model.table_input_models;

import controller.kontroler_formi.KontrolerFormeZaPrijavu;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;

public class PopisivacInputModel extends KorisnikInputModel {
    public PopisivacInputModel(KorisnikSistema korisnikSistema) {
        super(korisnikSistema);
        if(KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof ClanPKLS) {
            var brisanjeButton = new SimpleObjectProperty<>(new Button("Obriši"));
            var izmjenaButton = new SimpleObjectProperty<>(new Button("Izmjeni"));
            Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
        }
        else
        {
            var pregledajAktivnosti = new SimpleObjectProperty<>(new Button("Pregled aktivnosti"));
            Buttoni = new SimpleObjectProperty[] { pregledajAktivnosti };
        }

    }
}
