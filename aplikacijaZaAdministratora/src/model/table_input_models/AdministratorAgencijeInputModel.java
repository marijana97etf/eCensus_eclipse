package model.table_input_models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import model.korisnicki_nalozi.AdministratorAgencije;
import test.Aplikacija;

public class AdministratorAgencijeInputModel extends KorisnikInputModel{
    public AdministratorAgencijeInputModel(AdministratorAgencije korisnikSistema)
    {
        super(korisnikSistema);
        var brisanjeButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Obriši")));
        var izmjenaButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Izmjeni")));
        Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
    }
}
