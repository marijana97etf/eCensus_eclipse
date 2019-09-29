package model.table_input_models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import model.korisnicki_nalozi.DEInstruktor;
import test.Aplikacija;

public class DEInstruktorInputModel extends KorisnikInputModel {
    public DEInstruktorInputModel(DEInstruktor korisnikSistema)
    {
        super(korisnikSistema);
        var brisanjeButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Obriši")));
        var izmjenaButton = new SimpleObjectProperty<>(new Button(Aplikacija.prevediRecenicu("Izmjeni")));
        Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
    }
}
